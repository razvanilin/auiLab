package com.razvanilin.auiLab.photo.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Photo {
    private boolean flipped;
    private ArrayList<PhotoLine> photoLines;
    private BufferedImage photo;

    public Photo(String path) {
        try {
            System.out.println(new File(".").getCanonicalPath() + "\\" + path);
            photo = ImageIO.read(new File(new File(".").getCanonicalPath() + "\\" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLine(PhotoLine photoLine) {
        photoLines.add(photoLine);
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipStatus) {
        flipped = flipStatus;
    }

    public BufferedImage getPhoto() {
        return photo;
    }
}
