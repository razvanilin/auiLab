package com.razvanilin.auiLab.app.controller;

import com.razvanilin.auiLab.app.model.Status;
import com.razvanilin.auiLab.app.view.StatusView;

public class StatusController {
    private StatusView view;
    private Status model;

    public StatusController() {
        setView(new StatusView());
        setModel(new Status("Status"));
    }

    public void setStatus(String status) {
        model.setLabel("Status: " + status);
        view.render(this);
    }

    public Status getModel() {
        return model;
    }

    public StatusView getView() {
        return view;
    }

    private void setView(StatusView view) {
        this.view = view;
    }

    private void setModel(Status model) {
        this.model = model;
    }
}
