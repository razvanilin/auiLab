package com.razvanilin.auiLab.annotation;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class StrokeAnnotation extends Annotation {
    private boolean selected = false;
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
            g.setPaint(Color.RED);
        } else {
            g.setPaint(Color.BLACK);
        }
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
                selected = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public void deselect() {
        selected = false;
    }
}
