package com.razvanilin.auiLab.category.model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CategoryModel {
    private Dimension buttonSize;
    private String[] categories;
    private ArrayList<ActionListener> actionListeners = new ArrayList<>();
    private ArrayList<ChangeListener> changeListeners = new ArrayList<>();

    public CategoryModel() {
        categories = new String[]{"People", "Places", "School", "Travel"};
        buttonSize = new Dimension(50, 30);
    }

    public void setButtonSize(Dimension dimension) {
        buttonSize = dimension;
    }

    public Dimension getButtonSize() {
        return buttonSize;
    }

    public String[] getCategories() {
        return categories;
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

    private void fireActionListener() {
        for (ActionListener listener: actionListeners) {
            listener.actionPerformed(new ActionEvent(this, (int) System.currentTimeMillis(), "some action"));
        }
    }
}
