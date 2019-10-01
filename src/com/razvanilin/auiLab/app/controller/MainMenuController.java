package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.view.MainMenu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MainMenuController {
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
        menuView.getDeleteMenuItem().addActionListener(this::deleteClicked);
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

    private void deleteClicked(ActionEvent e) {
        statusController.setStatus("The image was removed");
        mainController.deletePhoto();
    }
}
