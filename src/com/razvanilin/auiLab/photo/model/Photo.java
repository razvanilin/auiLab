package com.razvanilin.auiLab.photo.model;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
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

    public Photo(String path) {
        setPhoto(path);
    }

    public Photo() { }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipStatus) {
        flipped = flipStatus;
    }

    public void setPhoto(String path) {
        try {
            photo = ImageIO.read(new File(path));
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
    }

    public HashMap<Integer, ArrayList<Pair<Integer, Integer>>> getLines() {
        return allLines;
    }

    public void removeLine(int key) {
        allLines.remove(key);
    }

    public void addText(Point origin, ArrayList<String> text) {
        allText.put(origin, text);
    }

    public HashMap<Point, ArrayList<String>> getAllText() {
        return allText;
    }
}
