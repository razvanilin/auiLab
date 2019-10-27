package com.razvanilin.auiLab.photo.controller;

import com.razvanilin.auiLab.photo.model.Toolbar;
import com.razvanilin.auiLab.photo.view.ToolbarView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ToolbarController extends JComponent {
    private Toolbar model;
    private ToolbarView view;
    private PhotoController photo;

    public ToolbarController(PhotoController photo) {
        this.photo = photo;
        setModel(new Toolbar());
        setView(new ToolbarView(this));
        setPreferredSize(new Dimension(300, 50));
    }

    public void setModel(Toolbar model) {
        this.model = model;
    }

    public Toolbar getModel() {
        return model;
    }

    public void setView(ToolbarView view) {
        this.view = view;
    }

    @Override
    public void paintComponent(Graphics g) {
        view.paint(g);
    }

    public void toolClicked(String key) {
        model.setShape(key);
    }

    public void chooseColor(ActionEvent e) {
        Color newColor = JColorChooser.showDialog(
                this,
                "Choose Shape Color",
                this.getBackground());
        if(newColor != null) {
            model.setColor(newColor);
        }
    }

    public void toggleMove() {
        model.toggleMove();
    }
}
