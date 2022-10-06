package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TileManager {
    GamePanel gp;
    Tile tiles[];
    int map[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles=new Tile[11];
        map=new int[gp.maxworldrow][gp.maxworldcol];
        setuptiles();
        loadmap();
    }
    public void printmap(int map2[][])
    {
        int num;
        for(int i=0;i< map2.length;i++)
            for(int j=0;j< map2.length;j++) {
                num = map2[i][j];
                System.out.println(num);
            }
    }
    public void loadmap(){
        try {
            HashMap<String, Integer> dict = new HashMap<String, Integer>();
            dict.put("a",10);
            dict.put("b",11);
            dict.put("c",12);
            dict.put("d",13);
            dict.put("e",14);
            dict.put("f",15);
            dict.put("g",16);
            BufferedReader filereader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("Map")));//opening map file to read from
            int i=0;
            while (i<gp.maxworldrow){
                String line=filereader.readLine();
                String[] splitedline = line.split(" ");
                for(int j=0;j<gp.maxworldcol;j++) {
                    //System.out.println(splitedline[j]);
                    if(isnum(splitedline[j])==true)
                        map[i][j] = Integer.parseInt(splitedline[j]);
                    else
                        map[i][j] = dict.get(splitedline[j]);
                }
                i++;
            }
            filereader.close();
            printmap(map);

        }catch (Exception e){}


    }
    public Boolean isnum(String num)
    {
        try {
            int x = Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public void setuptiles(){
        try {
            tiles[0]=new Tile(ImageIO.read(new FileInputStream("src/stonefloortile.png")),false);
            tiles[1]=new Tile(ImageIO.read(new FileInputStream("src/woodtile.png")),false);
            tiles[2]=new Tile(ImageIO.read(new FileInputStream("src/dirttile.png")),false);
            tiles[3]=new Tile(ImageIO.read(new FileInputStream("src/bricktile.png")),false);
            tiles[4]=new Tile(ImageIO.read(new FileInputStream("src/grasstile.png")),false);
            tiles[5]=new Tile(ImageIO.read(new FileInputStream("src/bridgetile.png")),false);
            tiles[6]=new Tile(ImageIO.read(new FileInputStream("src/watertile.png")),false);
            tiles[7]=new Tile(ImageIO.read(new FileInputStream("src/bedtile.png")),false);
            tiles[8]=new Tile(ImageIO.read(new FileInputStream("src/closechesttile.png")),false);
            tiles[9]=new Tile(ImageIO.read(new FileInputStream("src/closechesttile.png")),false);
            tiles[10]=new Tile(ImageIO.read(new FileInputStream("src/bedtile.png")),false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldcol=0;
        int worldrow=0;

        while(worldcol<gp.maxworldcol&&worldrow<gp.maxworldrow){
            int tilenum=map[worldrow][worldcol];
            // System.out.println(tilenum);
            int worldx=worldcol*gp.tilesize;
            int worldy=worldrow*gp.tilesize;
            int scrnx1=worldx-gp.player.worldx +gp.player.scrnx;
            int scrny1=worldy-gp.player.worldy +gp.player.scrny;

            if(worldx+gp.tilesize>gp.player.worldx-gp.player.scrnx&&worldx-gp.tilesize<gp.player.worldx+gp.player.scrnx&&
                    worldy+gp.tilesize>gp.player.worldy-gp.player.scrny&&worldy-gp.tilesize<gp.player.worldy+gp.player.scrny)

                g2.drawImage(tiles[tilenum].image,scrnx1,scrny1,gp.tilesize, gp.tilesize, null);
            worldcol++;
            if(worldcol==gp.maxworldcol){
                worldcol=0;
                worldrow++;
            }

        }
    }
}