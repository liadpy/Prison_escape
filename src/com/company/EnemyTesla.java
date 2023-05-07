package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class EnemyTesla extends Entity implements Runnable {
    public Boolean alive =true;
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
        this.gp=gp;

        this.worldx = wrldx * GamePanel.tilesize - GamePanel.tilesize;
        this.worldy = wrldy * GamePanel.tilesize - GamePanel.tilesize;
        direction="up";

    }

    @Override
    public void run() {
        int cooldown=0;
        Random rand = new Random();

        while (alive) {
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
                    gp.player.take_dmg_from_tesla(-2);

                if(this.iscollision==false){
                switch (direction){
                    case "up":this.worldy -= speed;break;
                    case "down":this.worldy += speed;break;
                    case "left":this.worldx -= speed;break;
                    case "right":this.worldx += speed;break;
                    }
                }
                cooldown--;
                if(gp.player.worldy==this.worldy||gp.player.worldy+1==this.worldy||gp.player.worldy-1==this.worldy||gp.player.worldy+2==this.worldy||gp.player.worldy-2==this.worldy){//looking for player horizontaly checking range of 5 pixels

                    if(this.worldx-gp.player.worldx<0&&Math.abs(this.worldx-gp.player.worldx)<= gp.scrnwidth&&cooldown<=0)//shoot right (checking side to shoot&checking distance)
                    {
                        gp.tes_balls.add(new Tes_ball(this.worldx, this.worldy, gp, "right"));
                        Thread t1 = new Thread(gp.tes_balls.get(gp.tes_balls.size() - 1));
                        t1.start();
                        cooldown=60;
                        System.out.println("shooting right");
                    }

                    if(this.worldx-gp.player.worldx>0&&Math.abs(this.worldx-gp.player.worldx)<= gp.scrnwidth&&cooldown<=0)//shoot left
                    {
                        gp.tes_balls.add(new Tes_ball(this.worldx, this.worldy, gp, "left"));
                        Thread t1 = new Thread(gp.tes_balls.get(gp.tes_balls.size() - 1));
                        t1.start();
                        cooldown=60;
                        System.out.println("shooting left");
                    }
                }
                if(gp.player.worldx==this.worldx||gp.player.worldx+1==this.worldx||gp.player.worldx-1==this.worldx||gp.player.worldx+2==this.worldx||gp.player.worldx-2==this.worldx){//looking for player verticaly

                    if(this.worldy-gp.player.worldy<0&&Math.abs(this.worldx-gp.player.worldx)<= gp.scrnhight&&cooldown<=0)//shoot down
                    {
                        gp.tes_balls.add(new Tes_ball(this.worldx, this.worldy, gp, "down"));
                        Thread t1 = new Thread(gp.tes_balls.get(gp.tes_balls.size() - 1));
                        t1.start();
                        cooldown=60;
                        System.out.println("shooting down");
                    }

                    if(this.worldy-gp.player.worldy>0&&Math.abs(this.worldx-gp.player.worldx)<= gp.scrnhight&&cooldown<=0)//shoot up
                    {
                        gp.tes_balls.add(new Tes_ball(this.worldx, this.worldy, gp, "up"));
                        Thread t1 = new Thread(gp.tes_balls.get(gp.tes_balls.size() - 1));
                        t1.start();
                        cooldown=60;
                        System.out.println("shooting up");
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

