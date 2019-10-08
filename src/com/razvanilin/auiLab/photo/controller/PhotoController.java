package com.razvanilin.auiLab.photo.controller;

import com.razvanilin.auiLab.annotation.ShapeAnnotation;
import com.razvanilin.auiLab.annotation.StrokeAnnotation;
import com.razvanilin.auiLab.annotation.TextAnnotation;
import com.razvanilin.auiLab.photo.model.Photo;
import com.razvanilin.auiLab.photo.view.PhotoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;


public class PhotoController extends JComponent {
    private PhotoView view;
    private Photo model;
    private ToolbarController toolbarController;

    private boolean drawingActive = false;
    private boolean typingActive = false;
    private Point currentTextPos;

    private StrokeAnnotation currentStroke;
    private ShapeAnnotation currentShape;
    private TextAnnotation currentText;

    public PhotoController() {
        setView(new PhotoView(this));
        setModel(new Photo());
        setToolbar(new ToolbarController(this));
        // uncomment this to pre-load a dummy image
        try {
            setPhoto(new File(".").getCanonicalPath() + "\\assets\\picture1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                currentText = null;
            }

            drawingActive = true;
            if (toolbarController.getModel().getActiveShape().equals("Line")) {
                constructPath(true, e.getPoint().x, e.getPoint().y);
            } else if (toolbarController.getModel().getActiveShape().equals("Ellipse")) {
                Ellipse2D newEllipse = new Ellipse2D.Double(e.getPoint().x, e.getPoint().y, 0, 0);
                currentShape = new ShapeAnnotation(newEllipse, ShapeAnnotation.SHAPE_TYPE.ELLIPSE);
                model.addAnnotation(currentShape);
            } else if (toolbarController.getModel().getActiveShape().equals("Rectangle")) {
                Rectangle2D newRectangle = new Rectangle2D.Double(e.getPoint().x, e.getPoint().y, 0, 0);
                currentShape = new ShapeAnnotation(newRectangle, ShapeAnnotation.SHAPE_TYPE.RECTANGLE);
                model.addAnnotation(currentShape);
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (model.isFlipped() && drawingActive) {
            typingActive = false;
            switch (toolbarController.getModel().getActiveShape()) {
                case "Line": {
                    constructPath(false, e.getPoint().x, e.getPoint().y);
                    break;
                }
                case "Ellipse":
                case "Rectangle": {
                    constructShape(e.getPoint());
                    break;
                }
                default:
                    break;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
       if (drawingActive) {
           drawingActive = false;
       }
    }

    public void keyTyped(KeyEvent e) {
        if (typingActive && model.isFlipped()) {
            if (currentText == null) {
                currentText = new TextAnnotation(20);
                currentText.setPosition(currentTextPos);
                currentText.setBounds(new Rectangle(model.getPhoto().getWidth(), model.getPhoto().getHeight()));
                model.addAnnotation(currentText);
            }

            currentText.addToString(String.valueOf(e.getKeyChar()));
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            currentText.removeLast();
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            currentText.removeLast();
        }
    }

    /* Private methods */
    private void setView(PhotoView view) {
        this.view = view;
    }

    private void setModel(Photo model) {
        this.model = model;
        addPhotoActionListener();
        addPhotoChangeListener();
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
            if (isInitialPoint) {
                currentStroke = new StrokeAnnotation(new Point(mouseX, mouseY));
                model.addAnnotation(currentStroke);
            } else {
                currentStroke.addPoint(new Point(mouseX, mouseY));
            }
        }
    }

    private void constructShape(Point point) {
        int boardX = this.getX() + view.getPadding();
        int boardY = this.getY();
        int boardW = model.getPhoto().getWidth() + this.getX() + view.getPadding();
        int boardH = model.getPhoto().getHeight() + this.getY();

        if (point.getX() > boardX && point.getX() < boardW && point.getY() > boardY && point.getY() < boardH) {
            currentShape.resize(point);
        }
    }

    private void addPhotoActionListener() {
        model.addActionListener(e -> {
            if (e.getActionCommand().equals("NEW_SHAPE")) {
                repaint();
            }
        });
    }

    private void addPhotoChangeListener() {
        model.addChangeListener(e -> repaint());
    }
}
