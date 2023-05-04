package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class EnemyTesla extends Entity implements Runnable {
    public EnemyTesla(int wrldx, int wrldy,GamePanel gp) {
        speed = 3;
        try {
            up1 = ImageIO.read(new FileInputStream("src/objectspics/tesla.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        solid = new Rectangle(3, 3, 39, 39);
        solidareadefultx = solid.x;
        solidareadefulty = solid.y;
        hp = 1;
        this.gp=gp;

        this.worldx = wrldx * GamePanel.tilesize - GamePanel.tilesize;
        this.worldy = wrldy * GamePanel.tilesize - GamePanel.tilesize;
        direction="up";
    }

    @Override
    public void run() {

        Random rand = new Random();

        while (true) {
            int num=rand.nextInt((4 - 1) + 1) + 1;

            if(num ==1){this.direction="up";}
            else if(num ==2){this.direction="down";}
            else if(num ==3){this.direction="left";}
            else if(num ==4){this.direction="right";}
            int n=rand.nextInt((50 - 30) + 30) + 30;
            gp.collisionDetector.checktile(this,gp.sem);
            for(int i=0;i<n;i++){
                gp.collisionDetector.checktile(this,gp.sem);
                Boolean hit_player=gp.collisionDetector.checkplayer(this,gp.sem);
                if(hit_player)
                    gp.player.take_dmg_from_tesla(1);

                if(this.iscollision==false){
                switch (direction){
                    case "up":this.worldy -= speed;break;
                    case "down":this.worldy += speed;break;
                    case "left":this.worldx -= speed;break;
                    case "right":this.worldx += speed;break;
                    }
                }
                try {
                    Thread.sleep(1000/30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

    }
    public void draw(Graphics2D g2,GamePanel gp)
    {
        int scrnx1=worldx-gp.player.worldx +gp.player.scrnx;
        int scrny1=worldy-gp.player.worldy +gp.player.scrny;

        if(worldx+gp.tilesize>gp.player.worldx-gp.player.scrnx&&worldx-gp.tilesize<gp.player.worldx+gp.player.scrnx&&
                worldy+gp.tilesize>gp.player.worldy-gp.player.scrny&&worldy-gp.tilesize<gp.player.worldy+gp.player.scrny)

            g2.drawImage(up1,scrnx1,scrny1,gp.tilesize, gp.tilesize, null);
    }
}

