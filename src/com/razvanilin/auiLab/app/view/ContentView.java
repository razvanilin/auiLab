package com.razvanilin.auiLab.app.view;

import com.razvanilin.auiLab.app.controller.ContentController;
import com.razvanilin.auiLab.photo.controller.PhotoController;

import javax.swing.*;
import java.awt.*;

public class ContentView extends JPanel {
    private ContentController ctrl;
    private PhotoController photo;

   public ContentView(ContentController ctrl) {
       this.ctrl = ctrl;
       this.photo = ctrl.getPhotoController();
       setup();
   }

   private void setup() {
       this.setLayout(new BorderLayout());
       this.add(new JLabel("Photo Main Content"), BorderLayout.NORTH);
       this.add(photo, BorderLayout.CENTER);
   }

   public void refreshPhoto() {
       this.remove(photo);
       this.repaint();
       this.validate();
       this.add(photo, BorderLayout.CENTER);
   }

   @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
   }
}
