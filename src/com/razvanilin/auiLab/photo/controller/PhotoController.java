package com.razvanilin.auiLab.photo.controller;

import com.razvanilin.auiLab.photo.model.Photo;
import com.razvanilin.auiLab.photo.view.PhotoView;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PhotoController extends JComponent {
    private PhotoView view;
    private Photo model;
    private boolean drawingActive = false;
    private boolean typingActive = false;

    private ArrayList<Pair<Integer, Integer>> currentLineCords = new ArrayList<>();
    private HashMap<Integer, ArrayList<Pair<Integer, Integer>>> allLines = new HashMap<>();

    private ArrayList<String> currentText = new ArrayList<>();
    private Point currentTextPos;
    private HashMap<Point, ArrayList<String>> allText = new HashMap<>();

    public PhotoController(String path) {
        setView(new PhotoView(this));
        setModel(new Photo(path));
    }

    public Photo getModel() {
        return this.model;
    }

    public void setPhoto(String path) {
       this.model.setPhoto(path);
    }

    public HashMap<Integer, ArrayList<Pair<Integer, Integer>>> getLines() {
        return allLines;
    }

    public HashMap<Point, ArrayList<String>> getAllText() {
        return allText;
    }

    @Override
    public void paintComponent(Graphics g) {
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
               allLines.remove(allLines.keySet().size());
           }
           currentLineCords = new ArrayList<>();
           repaint();
       }
    }

    public void keyTyped(KeyEvent e) {
        if (typingActive) {
            currentText.add(String.valueOf(e.getKeyChar()));
            allText.put(currentTextPos, currentText);
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
                allLines.put(allLines.keySet().size(), currentLineCords);
            } else {
                allLines.put(allLines.keySet().size() - 1, currentLineCords);
            }
            repaint();
        }
    }
}
