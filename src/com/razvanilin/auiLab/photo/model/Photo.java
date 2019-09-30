package com.razvanilin.auiLab.photo.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Photo {
    private boolean flipped;
    private BufferedImage photo;
    private int fontSize = 20;

    public Photo(String path) {
        setPhoto(path);
    }

    public Photo() { }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipStatus) {
        flipped = flipStatus;
    }

    public void setPhoto(String path) {
        try {
            photo = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getPhoto() {
        return photo;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
