package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class twomodeobj extends Obj {
    String mode;
    String angle;

    public twomodeobj(int worldx, int worldy, String mode, String angle,String name) {
        this.name = name;
        width = 16 * scale;
        height = 16 * scale;
        this.worldy = worldy * GamePanel.tilesize - GamePanel.tilesize;
        this.worldx = worldx * GamePanel.tilesize - GamePanel.tilesize;
        image=getimage(mode,angle);
        this.mode=mode;
        this.angle=angle;


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

    return null;}

}


