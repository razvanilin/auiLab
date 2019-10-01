package com.razvanilin.auiLab.category.view;

import com.razvanilin.auiLab.category.controller.CategoryMenuController;
import com.razvanilin.auiLab.category.model.CategoryModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CategoryMenuView extends JPanel {
    private CategoryModel categoryModel;
    private CategoryMenuController ctrl;
    private ArrayList<JToggleButton> categoryToggles;

    public CategoryMenuView(CategoryMenuController ctrl) {
        this.ctrl = ctrl;
    }

    public void setup(CategoryModel categoryModel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.categoryModel = categoryModel;
        this.setPreferredSize(new Dimension(80, 500));
    }

    public void render() {
        categoryToggles = new ArrayList<JToggleButton>();
        for(String category: categoryModel.getCategories()) {
            JToggleButton catBtn = new JToggleButton(category);
            catBtn.setPreferredSize(ctrl.getModel().getButtonSize());
            categoryToggles.add(catBtn);
            this.add(catBtn);
        }
    }

    public ArrayList<JToggleButton> getCategoryToggles() {
        return categoryToggles;
    }
}
