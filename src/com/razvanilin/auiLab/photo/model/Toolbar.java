package com.razvanilin.auiLab.photo.model;

public class Toolbar {
    private String[] shapes;
    private String activeShape;

    public Toolbar() {
        activeShape = "Line";
        shapes = new String[]{"Line", "Ellipse", "Rectangle"};
    }

    public String getActiveShape() {
        return activeShape;
    }

    public void setShape(String activeShape) {
        this.activeShape = activeShape;
    }
}
