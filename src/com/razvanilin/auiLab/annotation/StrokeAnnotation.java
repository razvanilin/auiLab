package com.razvanilin.auiLab.annotation;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class StrokeAnnotation extends Annotation {
    private Point startPoint;
    private ArrayList<Point> points = new ArrayList<>();
    public StrokeAnnotation(Point point) {
        startPoint = point;
        points.add(startPoint);
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(2));
        // DRAW THE LINES
        if (points.size() < 2) return;

        if (selected) {
            g.setStroke(new BasicStroke(4));
        }

        g.setPaint(this.color);

        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, points.size());
        path.moveTo(startPoint.x, startPoint.y);

        for (Point point : points) {
            path.lineTo(point.x, point.y);
        }

        g.draw(path);
    }

    @Override
    public boolean checkIfHit(int x, int y) {
        for (Point point : points) {
            if (
                    (point.x < x + 5 && point.x > x - 5)
                    && (point.y < y + 5 && point.y > y - 5)
            ) {
                if (this.selected) {
                    deselect();
                } else {
                    this.selected = true;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void move(Point position) {
        System.out.println("Move: " + position.x + " - " + position.y);
        for (Point point : points) {
            point.x += position.x;
            point.y += position.y;
        }
    }

    @Override
    public boolean isSelected() {
        return this.selected;
    }

    @Override
    public void deselect() {
        selected = false;
    }
}
