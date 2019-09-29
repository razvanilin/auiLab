package com.razvanilin.auiLab.app.view;

import com.razvanilin.auiLab.app.controller.StatusController;

import javax.swing.*;
import java.awt.*;

public class StatusView extends JPanel {
    private JLabel statusLabel;

    public StatusView() {
        statusLabel = new JLabel("Status");
        this.add(statusLabel);
    }

    public void render(StatusController ctrl) {
        statusLabel.setText(ctrl.getModel().getLabel());
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}
