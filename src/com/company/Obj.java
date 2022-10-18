package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Obj {
    String name;
    BufferedImage image;
    int worldx,worldy;
    Boolean collision=false;
    public Rectangle solidareaobj=new Rectangle(0,0,GamePanel.tilesize,GamePanel.tilesize);
    int solidareadefultx=0;
    int solidareadefulty=0;


    public void draw(Graphics2D g2,GamePanel gp)
    {
        int scrnx1=worldx-gp.player.worldx +gp.player.scrnx;
        int scrny1=worldy-gp.player.worldy +gp.player.scrny;

        if(worldx+gp.tilesize>gp.player.worldx-gp.player.scrnx&&worldx-gp.tilesize<gp.player.worldx+gp.player.scrnx&&
                worldy+gp.tilesize>gp.player.worldy-gp.player.scrny&&worldy-gp.tilesize<gp.player.worldy+gp.player.scrny)

            g2.drawImage(image,scrnx1,scrny1,gp.tilesize, gp.tilesize, null);
    }
}

