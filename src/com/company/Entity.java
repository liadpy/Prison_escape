package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    public int worldx,worldy;
    public int speed;
    public GamePanel gp;
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction="down";

    public Rectangle solid;
    public Boolean iscollision=false;
    int solidareadefultx;
    int solidareadefulty;
    Boolean timed_dmg=false;

    public int maxhp;
    public int hp;
}
