package com.company;

import java.awt.image.BufferedImage;

public class Tile {
    BufferedImage image;
    boolean colision;

    public Tile(BufferedImage image, boolean colision) {
        this.image = image;
        this.colision = colision;
    }
}
