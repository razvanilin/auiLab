package com.razvanilin.auiLab.photo.view;

import com.razvanilin.auiLab.photo.controller.ToolbarController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToolbarView {
    private ToolbarController ctrl;
    private ArrayList<JToggleButton> shapeToggles = new ArrayList<>();

    public ToolbarView(ToolbarController ctrl) {
        this.ctrl = ctrl;
        setup();
    }

    public void setup() {
        ctrl.setLayout(new BorderLayout());
        JToggleButton lineShape = new JToggleButton("Line");
        JToggleButton ellipseShape = new JToggleButton("Ellipse");
        JToggleButton rectangleShape = new JToggleButton("Rectangle");

        lineShape.addActionListener((key) -> {
            ctrl.toolClicked(key.getActionCommand());
            rectangleShape.setSelected(false);
            ellipseShape.setSelected(false);
        });
        ellipseShape.addActionListener((key) -> {
            ctrl.toolClicked(key.getActionCommand());
            rectangleShape.setSelected(false);
            lineShape.setSelected(false);
        });
        rectangleShape.addActionListener((key) -> {
            ctrl.toolClicked(key.getActionCommand());
            lineShape.setSelected(false);
            ellipseShape.setSelected(false);
        });


        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.add(lineShape);
        btnPanel.add(ellipseShape);
        btnPanel.add(rectangleShape);

        ctrl.add(btnPanel, BorderLayout.CENTER);
    }

    public void paint(Graphics g) {
    }
}
