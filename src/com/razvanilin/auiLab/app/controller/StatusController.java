package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.view.StatusView;

public class StatusController {
    private StatusView statusView;

    public StatusController(StatusView statusView) {
        this.statusView = statusView;
    }

    public void setStatus(String status) {
        statusView.getStatusLabel().setText("Status: " + status);
    }
}
