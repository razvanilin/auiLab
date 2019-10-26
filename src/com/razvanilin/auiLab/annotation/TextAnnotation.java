package com.razvanilin.auiLab.annotation;

import java.awt.*;
import java.util.ArrayList;

public class TextAnnotation extends Annotation {
    private ArrayList<String> text = new ArrayList<>();
    private Point position;
    private Rectangle bounds;
    private int fontSize;

    public TextAnnotation(int fontSize) {
        this.fontSize = fontSize;
        position = new Point(0,0);
        bounds = new Rectangle(0, 0);
    }

    public void addToString(String s) {
        text.add(s);
    }

    public void removeLast() {
        if (text.size() < 1) return;
        text.remove(text.size() - 1);
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setBounds(Rectangle r) {
        bounds = r;
    }

    @Override
    public void deselect() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, fontSize));

        StringBuilder stringToDraw = new StringBuilder();
        int typingHeight = position.y;
        boolean endReached = false;
        for (String s : text) {
            stringToDraw.append(s);
            // next, make sure the string doesn't get out of the picture by checking both x and y
            if (typingHeight >= bounds.getHeight() + 10) {
                endReached = true;
                break;
            } else if (g.getFontMetrics().stringWidth(stringToDraw.toString()) + position.x >= bounds.getWidth() - fontSize) {
                g.drawString(stringToDraw.toString(), position.x, typingHeight);
                typingHeight += fontSize;
                stringToDraw = new StringBuilder();
            }
        }
        if (stringToDraw.length() > 0 && !endReached) {
            g.drawString(stringToDraw.toString(), position.x, typingHeight);
        }
    }

    @Override
    public boolean checkIfHit(int x, int y) {
        return false;
    }
}
