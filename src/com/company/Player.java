package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyHandler;
    int c=0;
    int c2=0;
    int invis_c=0;
    int shooting_fire_cooldown_c=0;
    public final int scrnx;
    public final int scrny;
    Boolean key1=false;
    Boolean fireball=false;
    BufferedImage fullheart , blankhreat;
    int teslas_killed_c=0;


    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        worldx=2700;//spawn points
        worldy=1800;
        speed=4;
        setplayerimg();
        scrnx=gp.scrnwidth/2- gp.tilesize/2;
        scrny=gp.scrnhight/2-gp.tilesize/2;
        solid=new Rectangle(8,16,24,24);
        solidareadefultx=solid.x;
        solidareadefulty=solid.y;

        maxhp=3;
        hp=3;




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


            fullheart=ImageIO.read(new FileInputStream("src/objectspics/heart.png"));
            blankhreat=ImageIO.read(new FileInputStream("src/objectspics/emptyheart.png"));


        }catch (IOException e)
        {e.printStackTrace();}
    }
    public void update()
    {

        if(keyHandler.uppress==true||keyHandler.rightpress==true||keyHandler.leftpress==true||keyHandler.downpress==true) {

            if (keyHandler.gospeed == true) {
                speed=12;
            }
            else speed=4;

            if (keyHandler.uppress == true) {//w
                direction = "up";
            }
            if (keyHandler.downpress == true) {//s
                direction = "down";
            }
            if (keyHandler.leftpress == true) {//a
                direction = "left";
            }
            if (keyHandler.rightpress == true) {//d
                direction = "right";
            }
            if(keyHandler.spacepress==true&&fireball==true)//space to shoot a fireball
            {
                if (shooting_fire_cooldown_c<1) {
                    shooting_fire_cooldown_c=20;
                    gp.fire_balls.add(new Fire_ball(this.worldx, this.worldy, gp, this.direction));
                    Thread t1 = new Thread(gp.fire_balls.get(gp.fire_balls.size() - 1));
                    t1.start();
                }
            }
            if (shooting_fire_cooldown_c>0)
                shooting_fire_cooldown_c--;
            gp.collisionDetector.checktile(this,gp.sem);
            int obj_indx=gp.collisionDetector.checkObj(this,gp.sem);
            pickupobj(obj_indx);
            if(obj_indx!=-1)
            twomodeobj.openDoor(this,obj_indx);
            int tesindx =gp.collisionDetector.checkentity(this,gp.enemyTesla_array,gp.sem);//checking if player about to touch a tesla
            take_dmg_from_tesla(tesindx);
            if(this.iscollision==false)
                switch (direction){
                    case "up":worldy -= speed;break;
                    case "down":worldy += speed;break;
                    case "left":worldx -= speed;break;
                    case "right":worldx += speed;break;

                }
            c2++;
            if (c2 > 10) {//sprite changeing
                c++;
                c2 = 0;
            }
        }
        if (timed_dmg==true)
        {
            invis_c++;
            if(invis_c>80){     //giving timed_dmg 1.5~ secs to be true
                invis_c=0;
                timed_dmg=false;
            }
        }


    }
    public synchronized void take_dmg_from_tesla(int tesindx){ // synchronized cuz dont want any double dmg ... while timed_dmg didnt start the timer of 1,5 secs
        if(tesindx!=-1&&!timed_dmg)  //if hitting a tesla
        {
            hp-=1;
            timed_dmg=true;     //preventing too much dmg in 1 strike
        }
    }
    public void pickupobj(int obj_indx) {
        if(obj_indx!=-1&&gp.objarr[obj_indx] instanceof Picupobj){
            if (((Picupobj) gp.objarr[obj_indx]).img=="src/objectspics/key1.png"){
                key1=true;
                gp.objarr[obj_indx]=null;
            }
            else if (((Picupobj) gp.objarr[obj_indx]).img=="src/objectspics/fireball.png")
            {
                fireball=true;
                gp.objarr[obj_indx]=null;
            }


        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image=changesprite();
        g2.drawImage(image,scrnx,scrny,48,48,null);


        //drawing player hp
        for (int i=0;i<hp;i++){
            g2.drawImage(fullheart,48+i*56,56,56,48,null);
        }
        for (int i=hp;i<maxhp-1;i++)
            g2.drawImage(blankhreat,48+i*56,56,56,56,null);
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
