package com.razvanilin.auiLab.annotation;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ShapeAnnotation extends Annotation {
    public enum SHAPE_TYPE {
        ELLIPSE, RECTANGLE
    }
    private Shape shape;
    private SHAPE_TYPE type;
    public ShapeAnnotation(Shape shape, SHAPE_TYPE type) {
        this.shape = shape;
        this.type = type;
    }

    public void resize(Point newPoint) {
        switch (type) {
            case ELLIPSE: {
                Ellipse2D tempShape = (Ellipse2D) shape;
                tempShape.setFrameFromCenter(new Point2D.Double(((Ellipse2D) shape).getCenterX(), ((Ellipse2D) shape).getCenterY()), newPoint);
                break;
            }
            case RECTANGLE: {
                Rectangle2D tempShape = (Rectangle2D) shape;
                tempShape.setFrameFromCenter(new Point2D.Double(tempShape.getCenterX(), tempShape.getCenterY()), newPoint);
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void deselect() {
        this.selected = false;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setPaint(this.color);
        g.fill(shape);
        if (this.selected) {
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(2));
        }
        g.draw(shape);
    }

    @Override
    public boolean checkIfHit(int x, int y) {
        if (type == SHAPE_TYPE.RECTANGLE) {
            Rectangle2D rect = (Rectangle2D) shape;
            if (x <= rect.getX() + rect.getWidth() && x >= rect.getX() && y <= rect.getY() + rect.getHeight() && y >= rect.getY()) {
                this.selected = true;
                System.out.println("clicked");
            }
        } else if (type == SHAPE_TYPE.ELLIPSE) {
            Ellipse2D ellipse = (Ellipse2D) shape;
            if (x <= ellipse.getCenterX() + (ellipse.getWidth() / 2) && x >= ellipse.getCenterX() - (ellipse.getWidth() / 2)
            && y <= ellipse.getCenterY() + (ellipse.getHeight() / 2) && y >= ellipse.getCenterY() - (ellipse.getHeight() / 2)) {
                this.selected = true;
                System.out.println("Selected ellipse");
            }
        }

        return false;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void move(Point point) {

    }
}
