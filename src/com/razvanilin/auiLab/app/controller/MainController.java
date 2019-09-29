package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.view.MainMenu;
import com.razvanilin.auiLab.app.view.MainView;
import com.razvanilin.auiLab.category.controller.CategoryMenuController;
import com.razvanilin.auiLab.category.view.CategoryMenuView;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/*
    Wires most of the application together at the moment
 */
public class MainController {
    private MainView view;
    private MainMenu mainMenu;
    private MainMenuController mainMenuController;
    private StatusController statusController;
    private ContentController contentController;
    private CategoryMenuView categoryMenuView;
    private CategoryMenuController categoryMenuController;

    public MainController() {
        renderViews();
        initControllers();
        setup();

        view.getFrame().setVisible(true);
    }

    private void renderViews() {
        view = new MainView();
        mainMenu = new MainMenu();
        contentController = new ContentController();
        categoryMenuView = new CategoryMenuView();

        view.setMenu(mainMenu);
        view.setMainContent(contentController.getView());

        statusController = new StatusController();
        view.setStatusBar(statusController.getView());

        view.setSideMenu(categoryMenuView);
    }

    /*
    Current way of passing the 'statusController' to all the controllers is a bit ugly
    Once the models will be implemented, I will most likely use the Observer pattern or something similar
     */
    private void initControllers() {
        mainMenuController = new MainMenuController(mainMenu, statusController);
        categoryMenuController = new CategoryMenuController(categoryMenuView, statusController);

        view.getFrame().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                if (statusController != null) {
                    statusController.setStatus("Window resized");
                }
            }
        });
    }

    private void setup() {
    }
}
