package com.razvanilin.auiLab.photo.model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Toolbar {
    private String[] shapes;
    private String activeShape;
    private ArrayList<ActionListener> actionListeners = new ArrayList<>();
    private Color color;
    private boolean move = false;

    public Toolbar() {
        activeShape = "Line";
        shapes = new String[]{"Line", "Ellipse", "Rectangle"};
    }

    public String getActiveShape() {
        return activeShape;
    }

    public void setShape(String activeShape) {
        this.activeShape = activeShape;
    }

    public void setColor(Color color) {
        this.color = color;
        fireListeners("CHANGE_COLOR");
    }

    public Color getColor() {
        return this.color;
    }

    public void toggleMove() {
        this.move = !this.move;
        if (!this.move) {
            fireListeners("MOVE_DEACTIVATED");
        }
    }

    public boolean isMoveActive() {
        return this.move;
    }

    public void addActionListener(ActionListener listener) {
        actionListeners.add(listener);
    }

    private void fireListeners(String actionCommand) {
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(new ActionEvent(this, (int) System.currentTimeMillis(), actionCommand));
        }
    }
}
