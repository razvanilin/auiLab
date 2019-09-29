package com.razvanilin.auiLab.app.view;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private JFrame frame;

    public MainView() {
        setup();
    }

    private void setup() {
        frame = new JFrame("PhotoApp");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
    }

    public void setMenu(JMenuBar menuBar) {
        frame.setJMenuBar(menuBar);
    }

    public void setStatusBar(JPanel statusView) {
        frame.add(statusView, BorderLayout.SOUTH);
    }

    public void setSideMenu(JPanel sideMenu) {
        frame.add(sideMenu, BorderLayout.WEST);
    }

    public void setMainContent(JComponent mainContent) {
        frame.add(mainContent, BorderLayout.CENTER);
    }

    public JFrame getFrame() {
        return frame;
    }
}
