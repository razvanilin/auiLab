package com.razvanilin.auiLab.photo.view;

import com.razvanilin.auiLab.photo.controller.PhotoController;

import javax.swing.*;
import java.awt.*;

public class PhotoView {

    public void paint(Graphics g, PhotoController ctrl) {
        g.drawImage(ctrl.getModel().getPhoto(), 0, 0, ctrl);
    }
}
