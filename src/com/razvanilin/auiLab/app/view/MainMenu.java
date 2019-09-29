package com.razvanilin.auiLab.app.view;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JMenuBar {
    private JMenu fileMenu;

    private JMenuItem importMenuItem;
    private JMenuItem deleteMenuItem;
    private JMenuItem quitMenuItem;

    private JMenu viewMenu;
    private JMenuItem photoMenuItem;
    private JMenuItem browserMenuItem;

    public MainMenu() {
        setup();
    }

    private void setup() {
        // TODO: move the structure of the menu into a Menu model
        fileMenu = new JMenu("File");
        importMenuItem = new JMenuItem("Import");
        deleteMenuItem = new JMenuItem("Delete");
        quitMenuItem = new JMenuItem("Quit");
        fileMenu.add(importMenuItem);
        fileMenu.add(deleteMenuItem);
        fileMenu.add(quitMenuItem);

        viewMenu = new JMenu("View");
        photoMenuItem = new JMenuItem("Photo Viewer");
        browserMenuItem = new JMenuItem("Browser");
        viewMenu.add(photoMenuItem);
        viewMenu.add(browserMenuItem);

        this.add(fileMenu);
        this.add(viewMenu);

        this.setVisible(true);
    }

    public JMenuItem getImportMenuItem() {
        return importMenuItem;
    }

    public JMenuItem getDeleteMenuItem() {
        return deleteMenuItem;
    }

    public JMenuItem getQuitMenuItem() {
        return quitMenuItem;
    }

    public JMenuItem getPhotoMenuItem() {
        return photoMenuItem;
    }

    public JMenuItem getBrowserMenuItem() {
        return browserMenuItem;
    }
}
