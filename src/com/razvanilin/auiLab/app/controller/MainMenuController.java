package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.view.MainMenu;
import com.razvanilin.auiLab.app.view.StatusView;

public class MainMenuController {
    private MainMenu menuView;
    private StatusController statusController;

    public MainMenuController(MainMenu menuView, StatusController statusController) {
        this.menuView = menuView;
        this.statusController = statusController;
        setup();
    }

    private void setup() {
        menuView.getQuitMenuItem().addActionListener((e) -> System.exit(0));
        menuView.getImportMenuItem().addActionListener((e) -> statusController.setStatus("Import clicked"));
        menuView.getBrowserMenuItem().addActionListener((e) -> statusController.setStatus("Browser view clicked"));
        menuView.getDeleteMenuItem().addActionListener((e) -> statusController.setStatus("Delete clicked"));
        menuView.getPhotoMenuItem().addActionListener((e) -> statusController.setStatus("Photo View clicked"));
    }
}
