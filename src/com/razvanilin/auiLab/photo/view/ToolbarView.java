package com.razvanilin.auiLab.photo.view;

import com.razvanilin.auiLab.photo.controller.ToolbarController;

import javax.swing.*;
import java.awt.*;

public class ToolbarView {
    private ToolbarController ctrl;
    private JToggleButton lineShape = new JToggleButton("Line");
    private JToggleButton ellipseShape = new JToggleButton("Ellipse");
    private JToggleButton rectangleShape = new JToggleButton("Rectangle");

    public ToolbarView(ToolbarController ctrl) {
        this.ctrl = ctrl;
        setup();
    }

    public void setup() {
        ctrl.setLayout(new BorderLayout());

        lineShape.setSelected(true);
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

        JButton colorChooserBtn = new JButton("Color");
        colorChooserBtn.addActionListener(e -> {
            ctrl.chooseColor(e);
        });

        JToggleButton moveBtn = new JToggleButton("Move");

        moveBtn.addActionListener(e -> {
            ctrl.toggleMove();
            setShapeEnabled(!moveBtn.isSelected());
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.add(lineShape);
        btnPanel.add(ellipseShape);
        btnPanel.add(rectangleShape);

        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setMaximumSize(new Dimension(20, 1));

        btnPanel.add(separator);
        btnPanel.add(colorChooserBtn);
        btnPanel.add(moveBtn);

        ctrl.add(btnPanel, BorderLayout.CENTER);
    }

    private void setShapeEnabled(boolean status) {
        lineShape.setEnabled(status);
        ellipseShape.setEnabled(status);
        rectangleShape.setEnabled(status);
    }
}
