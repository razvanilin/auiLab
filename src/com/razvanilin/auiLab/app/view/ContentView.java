package com.razvanilin.auiLab.app.view;

import com.razvanilin.auiLab.app.controller.ContentController;
import com.razvanilin.auiLab.photo.controller.PhotoController;
import com.razvanilin.auiLab.photo.controller.ToolbarController;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;

public class ContentView extends JPanel {
    private ContentController ctrl;
    private PhotoController photo;
    private JScrollPane scrollPane;

   public ContentView(ContentController ctrl) {
       this.ctrl = ctrl;
       this.photo = ctrl.getPhotoController();
       setup();
   }

   private void setup() {
       JLabel placeholder = new JLabel("Choose 'File' > 'Import' to load an image");
       placeholder.setForeground(Color.white);
       placeholder.setFont(new Font(this.getFont().getFontName(), Font.BOLD, 30));
       this.setLayout(new BorderLayout());
       scrollPane = new JScrollPane();

       if (photo.getModel().getPhoto() != null) {
           scrollPane.getViewport().add(photo);
       } else {
           scrollPane.getViewport().add(placeholder);
       }
       scrollPane.getViewport().setBackground(Color.lightGray);
       this.add(photo.getToolbar(), BorderLayout.NORTH);
       this.add(scrollPane, BorderLayout.CENTER);
   }

   public void refreshPhoto() {
       this.removeAll();
       setup();
       this.repaint();
       this.validate();
   }

   @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
   }
}
