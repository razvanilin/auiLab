package com.razvanilin.auiLab.category.view;

import com.razvanilin.auiLab.category.model.CategoryModel;

import javax.swing.*;
import java.util.ArrayList;

public class CategoryMenuView extends JPanel {
    private CategoryModel categoryModel;
    private ArrayList<JToggleButton> categoryToggles;

    public CategoryMenuView() {
        setup();
    }

    private void setup() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        categoryModel = new CategoryModel();

        categoryToggles = new ArrayList<JToggleButton>();
        for(String category: categoryModel.getCategories()) {
            JToggleButton catBtn = new JToggleButton(category);
            categoryToggles.add(catBtn);
            this.add(catBtn);
        }
    }

    public ArrayList<JToggleButton> getCategoryToggles() {
        return categoryToggles;
    }
}
