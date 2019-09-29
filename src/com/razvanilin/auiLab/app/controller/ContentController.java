package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.view.ContentView;
import com.razvanilin.auiLab.photo.controller.PhotoController;

import javax.swing.*;
import java.awt.*;

public class ContentController {
    private ContentView view;
    private PhotoController photoController;

    public ContentController() {
        initializeControllers();
        setView(new ContentView(this));
    }

//    @Override
//    public void paintComponent(Graphics g) {
//        view.paint(g, this);
//        photoController.paint(g);
//    }

    private void setView(ContentView view) {
        this.view = view;
    }

    public ContentView getView() {
        return this.view;
    }

    private void initializeControllers() {
        photoController = new PhotoController("assets\\picture1.jpg");
    }

    public PhotoController getPhoto() {
        return photoController;
    }
}
