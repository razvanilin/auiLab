package com.razvanilin.auiLab.category.model;

public class CategoryModel {
    private String[] categories;

    public CategoryModel() {
        categories = new String[]{"People", "Places", "School", "Travel"};
    }

    public String[] getCategories() {
        return categories;
    }
}
