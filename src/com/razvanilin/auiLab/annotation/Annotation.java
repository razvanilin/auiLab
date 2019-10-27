package com.razvanilin.auiLab.annotation;

import java.awt.*;

public abstract class Annotation {
    Color color = Color.black;
    boolean selected = false;
    abstract public boolean isSelected();
    abstract public void deselect();
    abstract public void draw(Graphics2D g);
    abstract public boolean checkIfHit(int x, int y);
    abstract public void setColor(Color color);
}
