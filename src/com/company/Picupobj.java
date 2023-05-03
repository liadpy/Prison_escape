package com.company;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Picupobj extends Obj {
    String img;
    public Picupobj(int worldx, int worldy, String img) {
        name ="key1";
        try {
            image=ImageIO.read(new FileInputStream(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.img=img;
        this.worldy=worldy*GamePanel.tilesize-GamePanel.tilesize;
        this.worldx=worldx*GamePanel.tilesize-GamePanel.tilesize;
        collision=false;
    }
}
