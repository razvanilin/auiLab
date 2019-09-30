package com.razvanilin.auiLab.photo.controller;

import com.razvanilin.auiLab.photo.model.Photo;
import com.razvanilin.auiLab.photo.view.PhotoView;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class PhotoController extends JComponent {
    private PhotoView view;
    private Photo model;
    private boolean drawingActive = false;
    private boolean typingActive = false;

    private ArrayList<Pair<Integer, Integer>> currentLineCords = new ArrayList<>();

    private ArrayList<String> currentText = new ArrayList<>();
    private Point currentTextPos;

    public PhotoController() {
        setView(new PhotoView(this));
        setModel(new Photo());
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
       this.setPreferredSize(new Dimension(model.getPhoto().getWidth(), model.getPhoto().getHeight()));
    }

    @Override
    public void paintComponent(Graphics g) {
        this.setSize(model.getPhoto().getWidth(), model.getPhoto().getHeight());
        view.paint(g);
    }

    public void doubleClicked() {
        model.setFlipped(!model.isFlipped());
        repaint();
        validate();
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
            constructPath(true, e.getPoint().x, e.getPoint().y);
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (model.isFlipped() && drawingActive) {
            typingActive = false;
            constructPath(false, e.getPoint().x, e.getPoint().y);
        }
    }

    public void mouseReleased(MouseEvent e) {
       if (drawingActive) {
           drawingActive = false;
           if (currentLineCords.size() < 2) {
               model.removeLine(model.getLines().keySet().size());
           }
           currentLineCords = new ArrayList<>();
           repaint();
       }
    }

    public void keyTyped(KeyEvent e) {
        if (typingActive) {
            currentText.add(String.valueOf(e.getKeyChar()));
            model.addText(currentTextPos, currentText);
            repaint();
        }
    }

    /* Private methods */
    private void setView(PhotoView view) {
        this.view = view;
    }

    private void setModel(Photo model) {
        this.model = model;
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
            repaint();
        }
    }
}
