package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.view.ContentView;
import com.razvanilin.auiLab.photo.controller.PhotoController;

public class ContentController {
    private ContentView view;
    private PhotoController photoController;
    private StatusController statusController;

    public ContentController(StatusController statusController) {
        this.statusController = statusController;
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
        photoController = new PhotoController(statusController);
    }

    public PhotoController getPhotoController() {
        return photoController;
    }

    public void setPhoto(String path) {
        photoController.setPhoto(path);
        view.refreshPhoto();
    }

    // will eventually do more
    public void emptyCanvas() {
        photoController.removePhoto();
        view.refreshPhoto();
    }
}
