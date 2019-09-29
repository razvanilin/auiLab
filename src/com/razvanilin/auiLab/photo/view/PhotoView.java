package com.razvanilin.auiLab.photo.view;

import com.razvanilin.auiLab.photo.controller.PhotoController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PhotoView {

    public void paint(Graphics g, PhotoController ctrl) {
        BufferedImage photo = ctrl.getModel().getPhoto();
        if (photo != null) {
            g.drawImage(ctrl.getModel().getPhoto(), 0, 0, ctrl);
        }
    }
}
