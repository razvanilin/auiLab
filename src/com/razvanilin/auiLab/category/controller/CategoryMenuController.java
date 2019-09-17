package com.razvanilin.auiLab.category.controller;

import com.razvanilin.auiLab.app.controller.StatusController;
import com.razvanilin.auiLab.app.view.StatusView;
import com.razvanilin.auiLab.category.view.CategoryMenuView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CategoryMenuController {
    private CategoryMenuView categoryMenuView;
    private StatusController statusController;

    public CategoryMenuController(CategoryMenuView categoryMenuView, StatusController statusController) {
        this.categoryMenuView = categoryMenuView;
        this.statusController = statusController;
        setup();
    }

    private void setup() {
        for (JToggleButton btn: categoryMenuView.getCategoryToggles()) {
            btn.addActionListener(
                    e -> statusController.setStatus(
                        btn.getText() + " category clicked"
                    )
            );
        }
    }
}
