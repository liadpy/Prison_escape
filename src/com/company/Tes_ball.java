package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class Tes_ball extends Entity implements Runnable{

    public Tes_ball(int wrldx, int wrldy,GamePanel gp,String direction) {
        speed = 5;
        try {
            up1 = ImageIO.read(new FileInputStream("src/objectspics/tesball.png"));

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

        switch (this.direction){  // i dont want the ball to spawn on the tesla
            case "up":this.worldy -= 36;break;
            case "down":this.worldy += 36;break;
            case "left":this.worldx -= 36;break;
            case "right":this.worldx += 36;break;

        }
    }


    @Override
    public void run() {
        while (true){

            gp.collisionDetector.checktile(this,gp.sem);
            if(this.iscollision==true){
                gp.tes_balls.remove(this);
                break;
            }
            Boolean hit_player=gp.collisionDetector.checkplayer(this,gp.sem);//checking if ball touch the player
            if(hit_player){
                gp.player.take_dmg_from_tesla(1);
                gp.tes_balls.remove(this);
                System.out.println("i hit the player");
                break;
            }
                switch (this.direction){
                    case "up":this.worldy -= speed;break;
                    case "down":this.worldy += speed;break;
                    case "left":this.worldx -= speed;break;
                    case "right":this.worldx += speed;break;
                }
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
