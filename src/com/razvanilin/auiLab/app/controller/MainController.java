package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.model.MainModel;
import com.razvanilin.auiLab.app.view.MainMenu;
import com.razvanilin.auiLab.app.view.MainView;
import com.razvanilin.auiLab.category.controller.CategoryMenuController;
import com.razvanilin.auiLab.category.view.CategoryMenuView;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/*
    Wires most of the application together at the moment
 */
public class MainController {
    private MainView view;
    private MainModel model;
    private MainMenu mainMenu;
    private StatusController statusController;
    private ContentController contentController;
    private CategoryMenuController categoryMenuController;

    public MainController() {
        setModel(new MainModel());
        renderViews();
        initControllers();
        setup();

        view.getFrame().pack();
        view.getFrame().setSize(1280, 720);
        view.getFrame().setVisible(true);

    }

    public void importPhoto(String path) {
        contentController.setPhoto(path);
    }

    public void deletePhoto() {
        contentController.emptyCanvas();
    }

    public void addActionListener(ActionListener listener) {
        model.addActionListener(listener);
    }

    private void setModel(MainModel model) {
        this.model = model;
    }

    private void renderViews() {
        view = new MainView();

        // constructing the components and passing them to the view where they are placed in the main layout
        mainMenu = new MainMenu();
        view.setMenu(mainMenu);

        contentController = new ContentController();
        view.setMainContent(contentController.getView());

        statusController = new StatusController();
        view.setStatusBar(statusController.getView());

        categoryMenuController = new CategoryMenuController(statusController);
        view.setSideMenu(categoryMenuController.getView());
    }

    /*
    Current way of passing the 'statusController' to all the controllers is a bit ugly
    Once the models will be implemented, I will most likely use the Observer pattern or something similar
     */
    private void initControllers() {
        MainMenuController mainMenuController = new MainMenuController(mainMenu, this, statusController);
        CategoryMenuController categoryMenuController = new CategoryMenuController(statusController);

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
