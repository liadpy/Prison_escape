package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Fire_ball extends Entity implements Runnable{
    public Fire_ball(int wrldx, int wrldy,GamePanel gp,String direction) {
        speed = 5;
        try {
            up1 = ImageIO.read(new FileInputStream("src/objectspics/fireball.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        solid = new Rectangle(0, 0, 24, 24);
        solidareadefultx = solid.x;
        solidareadefulty = solid.y;

        this.gp=gp;
        this.worldx = wrldx;
        this.worldy = wrldy;
        this.direction=direction;

        switch (this.direction){  // i dont want the ball to spawn on the player
            case "up":worldy -= 36;break;
            case "down":worldy += 36;break;
            case "left":worldx -= 36;break;
            case "right":worldx += 36;break;

        }
    }


    @Override
    public void run() {

        while (true) {

            gp.collisionDetector.checktile(this,gp.sem);
            if(this.iscollision==true){
                gp.fire_balls.remove(this);
                break;
            }
            int hit_tesla=gp.collisionDetector.checkentity(this,gp.enemyTesla_array,gp.sem);
                if (hit_tesla!=-1)
                {
                    gp.enemyTesla_array[hit_tesla]=null;
                    gp.fire_balls.remove(this);
                    break;
                }
                if(this.iscollision==false){
                    switch (this.direction){
                        case "up":this.worldy -= speed;break;
                        case "down":this.worldy += speed;break;
                        case "left":this.worldx -= speed;break;
                        case "right":this.worldx += speed;break;
                    }
                }
                else break;
                try {
                    Thread.sleep(1000/30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    public void draw(Graphics2D g2,GamePanel gp)
    {
        int scrnx1=worldx-gp.player.worldx +gp.player.scrnx;
        int scrny1=worldy-gp.player.worldy +gp.player.scrny;

        if(worldx+gp.tilesize>gp.player.worldx-gp.player.scrnx&&worldx-gp.tilesize<gp.player.worldx+gp.player.scrnx&&
                worldy+gp.tilesize>gp.player.worldy-gp.player.scrny&&worldy-gp.tilesize<gp.player.worldy+gp.player.scrny)

            g2.drawImage(up1,scrnx1,scrny1,24, 24, null);
    }

    }
