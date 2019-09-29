package com.razvanilin.auiLab.photo.view;

import com.razvanilin.auiLab.photo.controller.PhotoController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PhotoView {
    private int padding;
    public PhotoView() {
        padding = 20;
    }

    public PhotoView(int padding) {
        this.padding = padding;
    }

    public void paint(Graphics g, PhotoController ctrl) {
        BufferedImage photo = ctrl.getModel().getPhoto();
        if (photo != null) {
            // calculate the size of the photo taking into consideration the aspect ratio
            float photoWidth = ctrl.getHeight() * ((float)photo.getWidth() / (float)photo.getHeight()) - 20;
            float photoHeight = ctrl.getWidth() * ((float)photo.getHeight() / (float)photo.getWidth()) - 20;

            // if the image is smaller than the new calculation, leave it with max original size so that it looks better
            if (photoWidth > photo.getWidth() || photoHeight > photo.getHeight()) {
                photoWidth = photo.getWidth();
                photoHeight = photo.getHeight();
            }

            g.drawImage(photo, 0, 0, (int)photoWidth, (int)photoHeight, null);
        }
    }
}
