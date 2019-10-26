package com.razvanilin.auiLab.photo.model;

import com.razvanilin.auiLab.annotation.Annotation;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Photo {
    private boolean drawing;
    private BufferedImage photo;
    private ArrayList<ActionListener> actionListeners = new ArrayList<>();
    private ArrayList<ChangeListener> changeListeners = new ArrayList<>();

    private ArrayList<Annotation> annotations = new ArrayList<>();

    public Photo(String path) {
        setPhoto(path);
    }

    public Photo() { }

    public boolean isDrawing() {
        return drawing;
    }

    public void setDrawing(boolean drawStatus) {
        drawing = drawStatus;
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

    public void addAnnotation(Annotation annotation) {
        annotations.add(annotation);
        fireActionListener("NEW_SHAPE");
    }

    public ArrayList<Annotation> getAnnotations() {
        return annotations;
    }

    public void deselectAnnotations(){
        for (Annotation annotation : annotations) {
            annotation.deselect();
        }
    }

    public void resetArt() {
        annotations = new ArrayList<>();
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
