package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class twomodeobj extends Obj {
    public static String mode;
    static String angle;

    public twomodeobj(int worldx, int worldy, String mode, String angle,String name,boolean collision) {
        this.name = name;
        this.worldy = worldy * GamePanel.tilesize - GamePanel.tilesize;
        this.worldx = worldx * GamePanel.tilesize - GamePanel.tilesize;
        this.image=getimage(mode,angle);
        this.mode=mode;
        this.angle=angle;
        this.collision=collision;
        }
    public BufferedImage getimage(String mode,String angle){
        try {
            if(name=="door1"){
                if(mode=="closed") {
                    if(angle=="top")
                        return ImageIO.read(new FileInputStream("src/objectspics/closedtopdoor.png"));
                    if(angle=="bottom")
                        return ImageIO.read(new FileInputStream("src/objectspics/closedbottomdoor.png"));
                    if(angle=="left")
                        return ImageIO.read(new FileInputStream("src/objectspics/closeddoorleft.png"));
                    if(angle=="right")
                        return ImageIO.read(new FileInputStream("src/objectspics/closeddoorright.png"));
                }else {
                    if (angle == "top")
                        return ImageIO.read(new FileInputStream("src/objectspics/opentopdoor.png"));
                    if (angle == "bottom")
                        return ImageIO.read(new FileInputStream("src/objectspics/openbottomdoor.png"));
                    if (angle == "left")
                        return ImageIO.read(new FileInputStream("src/objectspics/opendoorleft.png"));
                    if (angle == "right")
                        return ImageIO.read(new FileInputStream("src/objectspics/openrightdoor.png"));
                }

            }else if(name=="chest1"){
                if (mode=="open")
                    return ImageIO.read(new FileInputStream("src/objectspics/openchest1.png"));
                else return ImageIO.read(new FileInputStream("src/objectspics/closedchest1.png"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void openDoor(Player player,int indx){
        if(player.key1==true&&GamePanel.objarr[indx] instanceof twomodeobj&&GamePanel.objarr[indx].collision==true&&GamePanel.objarr[indx].name=="door1")//Todo check why door angle is always top-> its top cuz the last obj in objarr is top and probs cuz casting
        {
            try {
                GamePanel.objarr[indx].collision = false;
                ((twomodeobj) GamePanel.objarr[indx]).mode = "open";
                if (((twomodeobj) GamePanel.objarr[indx]).angle == "top")
                    GamePanel.objarr[indx].image = ImageIO.read(new FileInputStream("src/objectspics/opentopdoor.png"));
                else if (((twomodeobj) GamePanel.objarr[indx]).angle == "bottom")
                    GamePanel.objarr[indx].image = ImageIO.read(new FileInputStream("src/objectspics/openbottomdoor.png"));
                else if (((twomodeobj) GamePanel.objarr[indx]).angle == "left")
                    GamePanel.objarr[indx].image = ImageIO.read(new FileInputStream("src/objectspics/opendoorleft.png"));
                else if (((twomodeobj) GamePanel.objarr[indx]).angle == "right")
                    GamePanel.objarr[indx].image = ImageIO.read(new FileInputStream("src/objectspics/openrightdoor.png"));
                System.out.println(((twomodeobj) GamePanel.objarr[indx]).angle);

            }catch (Exception e){e.printStackTrace();}


        }
    }


}


