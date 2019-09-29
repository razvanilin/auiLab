package com.razvanilin.auiLab.app.view;

import com.razvanilin.auiLab.app.controller.ContentController;
import com.razvanilin.auiLab.photo.controller.PhotoController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ContentView extends JPanel {
    private ContentController ctrl;
    private PhotoController photo;

   public ContentView(ContentController ctrl) {
       this.ctrl = ctrl;
       this.photo = ctrl.getPhoto();
       setup();
   }

   private void setup() {
       this.setLayout(new BorderLayout());
       this.add(new JLabel("Photo Main Content"), BorderLayout.NORTH);
       this.add(photo, BorderLayout.CENTER);
   }

   @Override
   public void paintComponent(Graphics g) {
   }

   public void paint(Graphics g, ContentController ctrl) {
   }
}
