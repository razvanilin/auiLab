package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.view.ContentView;
import com.razvanilin.auiLab.photo.controller.PhotoController;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ContentController extends JComponent {
    private ContentView view;
    private PhotoController photoController;

    public ContentController() {
        initializeControllers();
        setView(new ContentView(this));
    }

    @Override
    public void paintComponent(Graphics g) {
        photoController.paint(g);
    }

    private void setView(ContentView view) {
        this.view = view;
    }

    public ContentView getView() {
        return this.view;
    }

    private void initializeControllers() {
        try {
            photoController = new PhotoController(new File(".").getCanonicalPath() + "\\assets\\picture1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PhotoController getPhotoController() {
        return photoController;
    }

    public void setPhoto(String path) {
        photoController.setPhoto(path);
        view.refreshPhoto();
    }
}
