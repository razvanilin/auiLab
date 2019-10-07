package com.razvanilin.auiLab.photo.controller;

import com.razvanilin.auiLab.photo.model.Photo;
import com.razvanilin.auiLab.photo.view.PhotoView;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class PhotoController extends JComponent {
    private PhotoView view;
    private Photo model;
    private ToolbarController toolbarController;

    private boolean drawingActive = false;
    private boolean typingActive = false;
    private ArrayList<Pair<Integer, Integer>> currentLineCords = new ArrayList<>();
    private Shape currentShape;
    private ArrayList<String> currentText = new ArrayList<>();
    private Point currentTextPos;

    public PhotoController() {
        setView(new PhotoView(this));
        setModel(new Photo());
        setToolbar(new ToolbarController(this));
        addPhotoActionListener();
        addPhotoChangeListener();
        // uncomment this to pre-load a dummy image
//        try {
//            setPhoto(new File(".").getCanonicalPath() + "\\assets\\picture1.jpg");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public Photo getModel() {
        return this.model;
    }

    public void setPhoto(String path) {
       this.model.setPhoto(path);
       this.model.setFlipped(false);
       this.model.resetArt();
       this.setPreferredSize(new Dimension(model.getPhoto().getWidth(), model.getPhoto().getHeight()));
    }

    public void removePhoto() {
        setModel(new Photo());
    }

    @Override
    public void paintComponent(Graphics g) {
        if (model.getPhoto() != null) {
            this.setSize(model.getPhoto().getWidth(), model.getPhoto().getHeight());
        }
        view.paint(g);
    }

    public void doubleClicked() {
        model.setFlipped(!model.isFlipped());
    }

    public void clicked() {
        this.requestFocus();
    }

    public void mousePressed(MouseEvent e) {
        if (model.isFlipped()) {
            if (!typingActive) {
                typingActive = true;
                currentTextPos = new Point(e.getPoint().x, e.getPoint().y);
            } else {
                typingActive = false;
                currentTextPos = null;
                currentText = new ArrayList<>();
            }

            drawingActive = true;
            if (toolbarController.getModel().getActiveShape().equals("Line")) {
                constructPath(true, e.getPoint().x, e.getPoint().y);
            } else if (toolbarController.getModel().getActiveShape().equals("Ellipse")) {
                currentShape = new Ellipse2D.Double(e.getPoint().x, e.getPoint().y, 0, 0);
                model.addShape(currentShape);
            } else if (toolbarController.getModel().getActiveShape().equals("Rectangle")) {
                currentShape = new Rectangle2D.Double(e.getPoint().x, e.getPoint().y, 0, 0);
                model.addShape(currentShape);
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (model.isFlipped() && drawingActive) {
            typingActive = false;
            if (toolbarController.getModel().getActiveShape().equals("Line")) {
                constructPath(false, e.getPoint().x, e.getPoint().y);
                Line2D shape = (Line2D) currentShape;
            } else if (toolbarController.getModel().getActiveShape().equals("Ellipse")) {
                constructEllipse(e.getPoint());
            } else if (toolbarController.getModel().getActiveShape().equals("Rectangle")) {
                constructRectangle(e.getPoint());
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
       if (drawingActive) {
           drawingActive = false;
           if (currentLineCords.size() < 2 && toolbarController.getModel().getActiveShape().equals("Line")) {
               model.removeLine(model.getLines().keySet().size());
           }
           currentLineCords = new ArrayList<>();
           currentShape = null;
       }
    }

    public void keyTyped(KeyEvent e) {
        if (typingActive && model.isFlipped()) {
            currentText.add(String.valueOf(e.getKeyChar()));
            model.addText(currentTextPos, currentText);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && currentText.size() > 0) {
            currentText.remove(currentText.size() - 1);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && currentText.size() > 0) {
            currentText.remove(currentText.size() - 1);
        }
    }

    /* Private methods */
    private void setView(PhotoView view) {
        this.view = view;
    }

    private void setModel(Photo model) {
        this.model = model;
    }

    private void setToolbar(ToolbarController toolbarController) {
        this.toolbarController = toolbarController;
    }

    public ToolbarController getToolbar() {
        return this.toolbarController;
    }

    private void constructPath(boolean isInitialPoint, int mouseX, int mouseY) {
        int boardX = this.getX() + view.getPadding();
        int boardY = this.getY();
        int boardW = model.getPhoto().getWidth() + this.getX() + view.getPadding();
        int boardH = model.getPhoto().getHeight() + this.getY();

        if (mouseX > boardX && mouseX < boardW && mouseY > boardY && mouseY < boardH) {
            Pair newPoint = new Pair(mouseX, mouseY);
            currentLineCords.add(newPoint);
            if (isInitialPoint) {
                model.addLine(model.getLines().keySet().size(), currentLineCords);
            } else {
                model.addLine(model.getLines().keySet().size() - 1, currentLineCords);
            }
        }
    }

    private void constructEllipse(Point2D point) {
        int boardX = this.getX() + view.getPadding();
        int boardY = this.getY();
        int boardW = model.getPhoto().getWidth() + this.getX() + view.getPadding();
        int boardH = model.getPhoto().getHeight() + this.getY();

        if (point.getX() > boardX && point.getX() < boardW && point.getY() > boardY && point.getY() < boardH) {
            Ellipse2D shape = (Ellipse2D) currentShape;
            shape.setFrameFromCenter(new Point2D.Double(((Ellipse2D) currentShape).getCenterX(), ((Ellipse2D) currentShape).getCenterY()), point);
        }
    }

    private void constructRectangle(Point2D point) {
        int boardX = this.getX() + view.getPadding();
        int boardY = this.getY();
        int boardW = model.getPhoto().getWidth() + this.getX() + view.getPadding();
        int boardH = model.getPhoto().getHeight() + this.getY();

        if (point.getX() > boardX && point.getX() < boardW && point.getY() > boardY && point.getY() < boardH) {
            Rectangle2D shape = (Rectangle2D) currentShape;
            shape.setFrameFromCenter(new Point2D.Double(shape.getCenterX(), shape.getCenterY()), point);
        }
    }

    private void addPhotoActionListener() {
        model.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("NEW_SHAPE")) {
                    repaint();
                }
            }
        });
    }

    private void addPhotoChangeListener() {
        model.addChangeListener(e -> repaint());
    }
}
