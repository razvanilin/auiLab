package com.razvanilin.auiLab.annotation;

import java.awt.*;

public abstract class Annotation {
    abstract public void deselect();
    abstract public void draw(Graphics2D g);
    abstract  public boolean checkIfHit(int x, int y);
}
