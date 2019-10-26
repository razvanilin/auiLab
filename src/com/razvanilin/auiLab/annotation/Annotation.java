package com.razvanilin.auiLab.annotation;

import fr.lri.swingstates.canvas.CShape;

import javax.swing.*;
import java.awt.*;

public abstract class Annotation extends JComponent {
    abstract public void draw(Graphics2D g);
    abstract public CShape getDrawObject();
}
