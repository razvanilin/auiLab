package com.razvanilin.auiLab.photo.view;

import com.razvanilin.auiLab.photo.controller.PhotoController;
import com.razvanilin.auiLab.photo.model.Photo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class PhotoView {
    private PhotoController ctrl;
    private int padding;

    public PhotoView(PhotoController ctrl) {
        this.ctrl = ctrl;
        setPadding(20);
        setupListeners();
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void paint(Graphics g) {
        Photo model = ctrl.getModel();
        BufferedImage photo = model.getPhoto();

        if (photo != null) {
            // calculate the size of the photo taking into consideration the aspect ratio
            // TODO: handle super small sizes (the ctrl might change size in a weird way)
            float photoWidth = ctrl.getHeight() * ((float) photo.getWidth() / (float) photo.getHeight()) - 20;
            float photoHeight = ctrl.getWidth() * ((float) photo.getHeight() / (float) photo.getWidth()) - 20;

            // if the image is smaller than the new calculation, leave it with max original size so that it looks better
            if (photoWidth > photo.getWidth() && photoHeight > photo.getHeight()) {
                photoWidth = photo.getWidth();
                photoHeight = photo.getHeight();
            }

            g.setColor(Color.WHITE);
            g.fillRect(padding, padding, (int)photoWidth, (int)photoHeight);

            if (!model.isFlipped()) {
                g.drawImage(photo, padding, padding, (int) photoWidth, (int) photoHeight, null);
            }
        }
    }

    private void setupListeners() {
        ctrl.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    ctrl.doubleClicked();
                } else {
                    ctrl.clicked();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
