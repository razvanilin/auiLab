package com.razvanilin.auiLab.annotation;

import java.awt.*;
import java.util.ArrayList;

public class TextAnnotation extends Annotation {
    private Dimension size = new Dimension(0, 0);
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
    public boolean isSelected() {
        return this.selected;
    }

    @Override
    public void deselect() {
        this.selected = false;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(this.color);
        int drawFontSize = fontSize;
        if (this.selected) {
            g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, drawFontSize));
        } else {
            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, drawFontSize));
        }

        StringBuilder stringToDraw = new StringBuilder();
        int typingHeight = position.y;
        boolean endReached = false;
        for (String s : text) {
            stringToDraw.append(s);
            // next, make sure the string doesn't get out of the picture by checking both x and y
            if (typingHeight >= bounds.getHeight() + 10) {
                endReached = true;
                break;
            } else if (g.getFontMetrics().stringWidth(stringToDraw.toString()) + position.x >= bounds.getWidth() - drawFontSize) {
                g.drawString(stringToDraw.toString(), position.x, typingHeight);
                typingHeight += drawFontSize;
                stringToDraw = new StringBuilder();
                // also update the width dimension of the string
                size.width = g.getFontMetrics().stringWidth(stringToDraw.toString());
            }
        }
        if (stringToDraw.length() > 0 && !endReached) {
            g.drawString(stringToDraw.toString(), position.x, typingHeight);

            if (size.width < g.getFontMetrics().stringWidth(stringToDraw.toString())) {
                size.width = g.getFontMetrics().stringWidth(stringToDraw.toString());
            }
        }

        // update the height dimension of the string
        size.height = typingHeight;
    }

    @Override
    public boolean checkIfHit(int x, int y) {
        System.out.println("Click: " + x + " - " + y);
        System.out.println("Pos:" + position.x + " - " + position.y);
        System.out.println("Size: " + (position.x + size.width) + " - " + (position.y + size.height));
        if (x <= position.x + size.width && x >= position.x && y <= position.y + size.height && y >= position.y) {
            System.out.println("Selected");
            this.selected = true;
            return true;
        }

        return false;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
