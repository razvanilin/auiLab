package com.razvanilin.auiLab.app.model;

import com.razvanilin.auiLab.app.AUIAction;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainModel {
    private ArrayList<ActionListener> actionListeners = new ArrayList<>();
    private ArrayList<ChangeListener> changeListeners = new ArrayList<>();

    public void addActionListener(ActionListener listener) {
        actionListeners.add(listener);
    }

    // TODO: use this to change the status instead of passing the StatusController everywhere
    public void addChangeListener(ChangeListener listener) {
        changeListeners.add(listener);
    }

    public void fireAction(AUIAction action) {
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(new ActionEvent(this, (int) System.currentTimeMillis(), action.toString()));
        }
    }
}
