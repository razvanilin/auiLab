package com.razvanilin.auiLab.category.controller;

import com.razvanilin.auiLab.app.controller.StatusController;
import com.razvanilin.auiLab.category.model.CategoryModel;
import com.razvanilin.auiLab.category.view.CategoryMenuView;

import javax.swing.*;

public class CategoryMenuController {
    private CategoryModel categoryModel;
    private CategoryMenuView categoryMenuView;
    private StatusController statusController;

    public CategoryMenuController(StatusController statusController) {
        this.statusController = statusController;

        categoryMenuView = new CategoryMenuView(this);
        categoryModel = new CategoryModel();

        setup();
        renderUI();
        registerListeners();
    }

    public CategoryMenuView getView() {
        return categoryMenuView;
    }

    public CategoryModel getModel() {
        return categoryModel;
    }

    private void setup() {
        categoryMenuView.setup(categoryModel);
    }

    private void renderUI() {
        categoryMenuView.render();
    }

    // TODO: change the logic a bit to move the controller reference to the view and call register listeners from there
    private void registerListeners() {
        for (JToggleButton btn: categoryMenuView.getCategoryToggles()) {
            btn.addActionListener(
                    e -> statusController.setStatus(
                            btn.getText() + " category clicked"
                    )
            );
        }
    }
}
