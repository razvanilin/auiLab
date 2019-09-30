package com.razvanilin.auiLab.photo.view;

import com.razvanilin.auiLab.photo.controller.PhotoController;
import com.razvanilin.auiLab.photo.model.Photo;
import javafx.util.Pair;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PhotoView {
    private PhotoController ctrl;
    private int padding;

    public PhotoView(PhotoController ctrl) {
        this.ctrl = ctrl;
        setPadding(0);
        setupListeners();
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getPadding() {
        return padding;
    }

    public void paint(Graphics g) {
        Photo model = ctrl.getModel();
        BufferedImage photo = model.getPhoto();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (photo != null) {
            // calculate the size of the photo taking into consideration the aspect ratio
            // TODO: handle super small sizes (the ctrl might change size in a weird way)
            float photoWidth = ctrl.getHeight() * ((float) photo.getWidth() / (float) photo.getHeight()) - 20;
            float photoHeight = ctrl.getWidth() * ((float) photo.getHeight() / (float) photo.getWidth()) - 20;

            // if the image is smaller than the new calculation, leave it with max original size so that it looks better
            if (photoWidth > photo.getWidth() && photoHeight > photo.getHeight()) {
                photoWidth = photo.getWidth();
                photoHeight = photo.getHeight();
            }

            g.setColor(Color.WHITE);
            g.fillRect(padding, padding, (int)photo.getWidth(), (int)photo.getHeight());

            if (!model.isFlipped()) {
                g.drawImage(photo, padding, padding, (int) photo.getWidth(), (int) photo.getHeight(), null);
            }

            // DRAW THE LINES
            if (model.isFlipped() && model.getLines().size() > 0) {
                g2d.setPaint(Color.BLACK);

                for (ArrayList<Pair<Integer, Integer>> cords : model.getLines().values()) {
                    if (cords.size() > 2) {
                        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, cords.size());
                        path.moveTo(cords.get(0).getKey(), cords.get(0).getValue());

                        for (Pair<Integer, Integer> pair : cords) {
                            path.lineTo(pair.getKey(), pair.getValue());
                        }

                        g2d.draw(path);
                    }
                }
            }

            // DRAW THE TEXT
            if (model.isFlipped()) {
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, ctrl.getModel().getFontSize()));

                for (Point point : model.getAllText().keySet()) {
                    ArrayList<String> text = model.getAllText().get(point);
                    StringBuilder stringToDraw = new StringBuilder();
                    int typingHeight = point.y;
                    boolean endReached = false;
                    for (String s : text) {
                        stringToDraw.append(s);
                        // next, make sure the string doesn't get out of the picture by checking both x and y
                        if (typingHeight >= photo.getHeight() + padding) {
                            endReached = true;
                            break;
                        } else if (g2d.getFontMetrics().stringWidth(stringToDraw.toString()) + point.x >= photo.getWidth()) {
                            g2d.drawString(stringToDraw.toString(), point.x, typingHeight);
                            typingHeight += model.getFontSize();
                            stringToDraw = new StringBuilder();
                        }
                    }
                    if (stringToDraw.length() > 0 && !endReached) {
                        g2d.drawString(stringToDraw.toString(), point.x, typingHeight);
                    }
                }
            }
        }
    }

    private void setupListeners() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    ctrl.doubleClicked();
                } else {
                    ctrl.clicked();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ctrl.mousePressed(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                ctrl.mouseDragged(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ctrl.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        ctrl.addMouseListener(mouseAdapter);
        ctrl.addMouseMotionListener(mouseAdapter);

        ctrl.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ctrl.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
