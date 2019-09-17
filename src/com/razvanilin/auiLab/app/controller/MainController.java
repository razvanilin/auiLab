package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.view.ContentView;
import com.razvanilin.auiLab.app.view.MainMenu;
import com.razvanilin.auiLab.app.view.MainView;
import com.razvanilin.auiLab.app.view.StatusView;
import com.razvanilin.auiLab.category.controller.CategoryMenuController;
import com.razvanilin.auiLab.category.view.CategoryMenuView;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainController {
    private MainView mainView;
    private MainMenu mainMenu;
    private MainMenuController mainMenuController;
    private StatusController statusController;
    private ContentView contentView;
    private StatusView statusView;
    private CategoryMenuView categoryMenuView;
    private CategoryMenuController categoryMenuController;

    public MainController() {
        renderViews();
        initControllers();
        setup();
    }

    private void renderViews() {
        mainView = new MainView();
        mainMenu = new MainMenu();
        statusView = new StatusView();
        contentView = new ContentView();
        categoryMenuView = new CategoryMenuView();

        mainView.setMenu(mainMenu);
        mainView.setMainContent(contentView);
        mainView.setStatusBar(statusView);
        mainView.setSideMenu(categoryMenuView);
        mainView.getFrame().setVisible(true);
    }

    /*
    Current way of passing the 'statusController' to all the controllers is a bit ugly
    Once the models will be implemented, I will most likely use the Observer pattern or something similar
     */
    private void initControllers() {
        statusController = new StatusController(statusView);

        mainMenuController = new MainMenuController(mainMenu, statusController);
        categoryMenuController = new CategoryMenuController(categoryMenuView, statusController);
    }

    private void setup() {
        mainView.getFrame().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                statusController.setStatus("Window resized");
            }
        });
    }
}
