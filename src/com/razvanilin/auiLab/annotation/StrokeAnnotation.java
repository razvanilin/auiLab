package com.razvanilin.auiLab.annotation;

import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CPolyLine;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.Canvas;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class StrokeAnnotation extends Annotation {
    private Point startPoint;
    private ArrayList<Point> points = new ArrayList<>();
    CExtensionalTag selectedTag;

    public StrokeAnnotation(Point point) {
        startPoint = point;
        points.add(startPoint);

        selectedTag = new CExtensionalTag() {
            @Override
            public void added(CShape s) {
                s.setOutlined(true).setStroke(new BasicStroke(4));
            }

            @Override
            public void removed(CShape s) {
                s.setStroke(new BasicStroke(2));
            }
        };
    }

    public void addPoint(Point point) {
        points.add(point);
        System.out.println("Added a point at: " + point.x + " - " + point.y);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setupCanvas(Canvas canvas) {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(2));
        // DRAW THE LINES
        if (points.size() < 2) return;
        g.setPaint(Color.BLACK);
        CPolyLine path = new CPolyLine(GeneralPath.WIND_EVEN_ODD, 2);

        path.moveTo(startPoint.x, startPoint.y);

        for (Point point : points) {
            path.lineTo(point.x, point.y);
        }
        path.addTag(selectedTag);
        g.draw(path.getShape());
    }

    @Override
    public CShape getDrawObject() {
        CPolyLine path = new CPolyLine(GeneralPath.WIND_EVEN_ODD, 2);
        path.moveTo(startPoint.x, startPoint.y);

        for (Point point : points) {
            path.lineTo(point.x, point.y);
        }

        path.addTag(selectedTag);

        return path;
    }
}
