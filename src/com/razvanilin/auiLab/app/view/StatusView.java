package com.razvanilin.auiLab.app.view;

import javax.swing.*;
import java.awt.*;

public class StatusView extends JPanel {
    private JLabel statusLabel;

    public StatusView() {
       setup();
    }

    private void setup() {
        statusLabel = new JLabel("Status");
        this.add(statusLabel);
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}
