package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyHandler;
    int c=0;
    int c2=0;
    public final int scrnx;
    public final int scrny;


    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        worldx=2000;//spawn points
        worldy=1700;
        speed=4;
        setplayerimg();
        scrnx=gp.scrnwidth/2- gp.tilesize/2;
        scrny=gp.scrnhight/2-gp.tilesize/2;

    }
    public void setplayerimg()
    {
        try {
            down1=ImageIO.read(new FileInputStream("src/playersprites/mydown1.png"));
            down2=ImageIO.read(new FileInputStream("src/playersprites/mydown2.png"));
            left1=ImageIO.read(new FileInputStream("src/playersprites/myleft1.png"));
            left2=ImageIO.read(new FileInputStream("src/playersprites/myleft2.png"));
            up1=ImageIO.read(new FileInputStream("src/playersprites/myup1.png"));
            up2=ImageIO.read(new FileInputStream("src/playersprites/myup2.png"));
            right1=ImageIO.read(new FileInputStream("src/playersprites/myright1.png"));
            right2=ImageIO.read(new FileInputStream("src/playersprites/myright2.png"));


        }catch (IOException e)
        {e.printStackTrace();}
    }
    public void update()
    {
        if(keyHandler.uppress==true||keyHandler.rightpress==true||keyHandler.leftpress==true||keyHandler.downpress==true||keyHandler.gospeed==true) {
            if (keyHandler.gospeed == true) {//w
                speed=12;
            }
            else speed=4;
            if (keyHandler.uppress == true) {//w
                worldy -= speed;
                direction = "up";
            }
            if (keyHandler.downpress == true) {//s
                worldy += speed;
                direction = "down";
            }
            if (keyHandler.leftpress == true) {//a
                worldx -= speed;
                direction = "left";
            }
            if (keyHandler.rightpress == true) {//d
                worldx += speed;
                direction = "right";
            }
            c2++;
            if (c2 > 10) {//sprite change after 1000/6 mili secs
                c++;
                c2 = 0;
            }
        }
    }
    public void draw(Graphics2D g2)
    {   BufferedImage image=changesprite();
        g2.drawImage(image,scrnx,scrny,48,48,null);
    }
    public BufferedImage changesprite()
    {

        BufferedImage image=null;
        switch (direction){
            case "up":
                if(c%2==0)
                    image=up1;
                else image=up2;
                break;
            case "down":
                if(c%2==0)
                    image=down1;
                else image=down2;
                break;
            case "left":
                if(c%2==0)
                    image=left1;
                else image=left2;
                break;
            case "right":
                if(c%2==0)
                    image=right1;
                else image=right2;
                break;
        }

        return image;
    }
}
