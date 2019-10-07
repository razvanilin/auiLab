package com.razvanilin.auiLab.photo.model;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Photo {
    private boolean flipped;
    private BufferedImage photo;
    private int fontSize = 20;
    private HashMap<Integer, ArrayList<Pair<Integer, Integer>>> allLines = new HashMap<>();
    private HashMap<Point, ArrayList<String>> allText = new HashMap<>();
    private ArrayList<Shape> shapeList = new ArrayList<>();
    private ArrayList<ActionListener> actionListeners = new ArrayList<>();
    private ArrayList<ChangeListener> changeListeners = new ArrayList<>();

    public Photo(String path) {
        setPhoto(path);
    }

    public Photo() { }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipStatus) {
        flipped = flipStatus;
        fireChangeListener();
    }

    public void setPhoto(String path) {
        try {
            photo = ImageIO.read(new File(path));
            fireChangeListener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getPhoto() {
        return photo;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void addLine(int key, ArrayList<Pair<Integer, Integer>> line) {
        allLines.put(key, line);
        fireActionListener("NEW_SHAPE");
    }

    public HashMap<Integer, ArrayList<Pair<Integer, Integer>>> getLines() {
        return allLines;
    }

    public void removeLine(int key) {
        allLines.remove(key);
        fireActionListener("NEW_SHAPE");
    }

    public void addText(Point origin, ArrayList<String> text) {
        allText.put(origin, text);
        fireActionListener("NEW_SHAPE");
    }

    public HashMap<Point, ArrayList<String>> getAllText() {
        return allText;
    }

    public ArrayList<Shape> getShapeList() {
        return shapeList;
    }

    public void addShape(Shape shape) {
        shapeList.add(shape);
        fireActionListener("NEW_SHAPE");
    }

    public void resetArt() {
        allLines = new HashMap<>();
        allText = new HashMap<>();
        shapeList = new ArrayList<>();
    }

    public void addActionListener(ActionListener listener) {
        actionListeners.add(listener);
    }

    public void addChangeListener(ChangeListener listener) {
        changeListeners.add(listener);
    }

    private void fireChangeListener() {
        for (ChangeListener listener: changeListeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    private void fireActionListener(String actionCommand) {
        for (ActionListener listener: actionListeners) {
            listener.actionPerformed(new ActionEvent(this, (int) System.currentTimeMillis(), actionCommand));
        }
    }
}
