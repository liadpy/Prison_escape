package com.company;

import java.awt.*;

public class CollisionDetector{

    GamePanel gp;

    public CollisionDetector(GamePanel gp) {
        this.gp = gp;

    }

    public void checktile(Entity entity)
    {
        int entityleftworldx=entity.worldx +entity.solid.x;
        int entityrightworldx=entity.worldx +entity.solid.x+entity.solid.width;
        int entitytopworldy=entity.worldy+ entity.solid.y;            //calc world x,y of the solid area
        int entitybottomworldy=entity.worldy+ entity.solid.y+entity.solid.height;

        int entityleftcol=entityleftworldx/gp.tilesize;
        int entityrightcol=entityrightworldx/gp.tilesize;
        int entitytoprow=entitytopworldy/gp.tilesize;       //calc row,col nums
        int entitybottomrow=entitybottomworldy/gp.tilesize;

        int tile1,tile2;

        switch (entity.direction)
        {
            case "up":
                entitytoprow=(entitytopworldy-entity.speed)/gp.tilesize;  // predict where player is gonna be
                tile1=gp.tileManager.map[entitytoprow][entityleftcol];
                tile2=gp.tileManager.map[entitytoprow][entityrightcol];
                if(gp.tileManager.tiles[tile1].colision==true||gp.tileManager.tiles[tile2].colision==true) //checking both tiles , if one of them is solid -> collision
                    entity.iscollision=true;
                else entity.iscollision=false;

                break;
            case "down":
                entitybottomrow=(entitybottomworldy+entity.speed)/gp.tilesize;  // predict where player is gonna be
                tile1=gp.tileManager.map[entitybottomrow][entityleftcol];
                tile2=gp.tileManager.map[entitybottomrow][entityrightcol];
                if(gp.tileManager.tiles[tile1].colision==true||gp.tileManager.tiles[tile2].colision==true) //checking both tiles , if one of them is solid -> collision
                    entity.iscollision=true;
                else entity.iscollision=false;
                break;
            case "left":
                entityleftcol=(entityleftworldx-entity.speed)/gp.tilesize;  // predict where player is gonna be
                tile1=gp.tileManager.map[entitybottomrow][entityleftcol];
                tile2=gp.tileManager.map[entitytoprow][entityleftcol];
                if(gp.tileManager.tiles[tile1].colision==true||gp.tileManager.tiles[tile2].colision==true) //checking both tiles , if one of them is solid -> collision
                    entity.iscollision=true;
                else entity.iscollision=false;
                break;
            case "right":
                entityrightcol=(entityrightworldx+entity.speed)/gp.tilesize;  // predict where player is gonna be
                tile1=gp.tileManager.map[entitybottomrow][entityrightcol];
                tile2=gp.tileManager.map[entitytoprow][entityrightcol];
                if(gp.tileManager.tiles[tile1].colision==true||gp.tileManager.tiles[tile2].colision==true) //checking both tiles , if one of them is solid -> collision
                    entity.iscollision=true;
                else entity.iscollision=false;
                break;
        }
    }
    public int checkObj(Entity entity){
        int indx=-1;
        for (int i=0;i<gp.objarr.length;i++){
            if(gp.objarr[i]!=null) {
                entity.solid.x += entity.worldx;//find entity's solid area pos
                entity.solid.y += entity.worldy;

                gp.objarr[i].solidareaobj.x += gp.objarr[i].worldx;//find obj's solid area pos
                gp.objarr[i].solidareaobj.y += gp.objarr[i].worldy;

                switch (entity.direction) {
                    case "up":
                        entity.solid.y -= entity.speed;
                        if (entity.solid.intersects(gp.objarr[i].solidareaobj)) {//check if the 2 rects are touching(solid areas)
                            if (gp.objarr[i].collision == true)
                                entity.iscollision = true;
                            else entity.iscollision = false;
                            if (entity instanceof Player) indx = i;
                        }
                        break;
                    case "down":
                        entity.solid.y += entity.speed;
                        if (entity.solid.intersects(gp.objarr[i].solidareaobj)) {
                            if (gp.objarr[i].collision == true)
                                entity.iscollision = true;
                            else entity.iscollision = false;
                            if (entity instanceof Player) indx = i;
                        }
                        break;
                    case "left":
                        entity.solid.x -= entity.speed;
                        if (entity.solid.intersects(gp.objarr[i].solidareaobj)) {
                            if (gp.objarr[i].collision == true)
                                entity.iscollision = true;
                            else entity.iscollision = false;
                            if (entity instanceof Player) indx = i;
                        }
                        break;
                    case "right":
                        entity.solid.x += entity.speed;
                        if (entity.solid.intersects(gp.objarr[i].solidareaobj)) {
                            if (gp.objarr[i].collision == true)
                                entity.iscollision = true;
                            else entity.iscollision = false;
                            if (entity instanceof Player) indx = i;
                        }
                        break;
                }
                entity.solid.x = entity.solidareadefultx;
                entity.solid.y = entity.solidareadefulty;
                gp.objarr[i].solidareaobj.x = gp.objarr[i].solidareadefultx;
                gp.objarr[i].solidareaobj.y = gp.objarr[i].solidareadefulty;
            }

        }
        System.out.println(indx);
        return indx;
    }

}
