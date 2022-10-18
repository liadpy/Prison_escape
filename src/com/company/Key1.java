package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Key1 extends Obj {
    public Key1(int worldx,int worldy) {
        name ="key1";
        try {
            image=ImageIO.read(new FileInputStream("src/objectspics/key1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.worldy=worldy*GamePanel.tilesize-GamePanel.tilesize;
        this.worldx=worldx*GamePanel.tilesize-GamePanel.tilesize;
        collision=false;
    }
}
