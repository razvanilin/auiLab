package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.AUIAction;
import com.razvanilin.auiLab.app.view.MainMenu;
import com.razvanilin.auiLab.app.view.StatusView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainMenuController extends JComponent {
    private MainMenu menuView;
    private StatusController statusController;
    private MainController mainController;

    public MainMenuController(MainMenu menuView, MainController mainController, StatusController statusController) {
        this.menuView = menuView;
        this.mainController = mainController;
        this.statusController = statusController;
        setup();
    }

    private void setup() {
        menuView.getQuitMenuItem().addActionListener((e) -> System.exit(0));
        menuView.getImportMenuItem().addActionListener(this::importClicked);
        menuView.getBrowserMenuItem().addActionListener((e) -> statusController.setStatus("Browser view clicked"));
        menuView.getDeleteMenuItem().addActionListener((e) -> statusController.setStatus("Delete clicked"));
        menuView.getPhotoMenuItem().addActionListener((e) -> statusController.setStatus("Photo View clicked"));
    }

    private void importClicked(ActionEvent e) {
        if (e.getSource() == menuView.getImportMenuItem()) {
            statusController.setStatus("Import clicked");
            JFileChooser fc = new JFileChooser();
            fc.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png"));
            fc.setAcceptAllFileFilterUsed(false);
            int finalAction = fc.showOpenDialog(menuView);

            if (finalAction == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    mainController.importPhoto(file.getCanonicalPath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
