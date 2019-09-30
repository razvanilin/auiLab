package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.view.ContentView;
import com.razvanilin.auiLab.photo.controller.PhotoController;

import java.io.File;
import java.io.IOException;

public class ContentController {
    private ContentView view;
    private PhotoController photoController;

    public ContentController() {
        initializeControllers();
        setView(new ContentView(this));
    }

    private void setView(ContentView view) {
        this.view = view;
    }

    public ContentView getView() {
        return this.view;
    }

    private void initializeControllers() {
        photoController = new PhotoController();
    }

    public PhotoController getPhotoController() {
        return photoController;
    }

    public void setPhoto(String path) {
        photoController.setPhoto(path);
        view.refreshPhoto();
    }
}
