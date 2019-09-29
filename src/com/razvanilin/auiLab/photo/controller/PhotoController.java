package com.razvanilin.auiLab.photo.controller;

import com.razvanilin.auiLab.photo.model.Photo;
import com.razvanilin.auiLab.photo.view.PhotoView;

import javax.swing.*;
import java.awt.*;


public class PhotoController extends JComponent {
    private PhotoView view;
    private Photo model;

    public PhotoController(String path) {
        setView(new PhotoView());
        setModel(new Photo(path));
    }

    public void flip() {
        model.setFlipped(!model.isFlipped());
    }

    public Photo getModel() {
        return this.model;
    }

    @Override
    public void paintComponent(Graphics g) {
        view.paint(g, this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    /* Private methods */
    private void setView(PhotoView view) {
        this.view = view;
    }

    private void setModel(Photo model) {
        this.model = model;
    }
}