package com.razvanilin.auiLab.photo.model;

import java.util.ArrayList;
import java.util.Arrays;

public class PhotoLine {
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public PhotoLine(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public ArrayList<Integer> getCoordinates() {
        return new ArrayList<>(Arrays.asList(x1, y1, x2, y2));
    }
}
