package com.company;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable  {
    static final int origianltilesize=16;//16x16 pixels
    public static final int scale=3;
    public static final int tilesize=origianltilesize*scale;//16x16 pix is very small so i make it bigger

    final int scrnrow=20;//12 tiles on vertical
    final int scrncol=24;//16 tiles on horizontal
    final int scrnhight=scrnrow*tilesize;//sum tiles*size of the tiles is the height of the scrn
    final int scrnwidth=scrncol*tilesize;//sum tiles*size of the tiles is the width of the scrn

    public final int maxworldcol=132;
    public final int maxworldrow=80;
    public final int worldwight=maxworldcol*tilesize;
    public final int worldheight=maxworldrow*tilesize;

    final int FPS=60;

    Thread gamethread;
    KeyHandler keyhandler=new KeyHandler();
    Player player=new Player(this,keyhandler);
    public static Obj objarr[]=new Obj[12];

    TileManager tileManager=new TileManager(this);
    public CollisionDetector collisionDetector=new CollisionDetector(this);

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(scrnwidth,scrnhight));//giveing the panel it's size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);//improve performance
        this.addKeyListener(keyhandler);
        this.setFocusable(true);//gamepanel can receive key input
        objarr[0]=new Picupobj(58,53 ,"src/objectspics/key1.png");
        objarr[1]=new twomodeobj(79,50,"open","top","door1",false);
        objarr[2]=new twomodeobj(79,61,"open","top","door1",false);
        objarr[3]=new twomodeobj(79,68,"open","bottom","door1",false);
        objarr[4]=new twomodeobj(91,58,"open","left","door1",false);
        objarr[5]=new twomodeobj(92,58,"closed","right","door1",true);
        objarr[6]=new twomodeobj(108,60,"closed","bottom","door1",true);
        objarr[7]=new twomodeobj(88,66,"closed","bottom","door1",true);
        objarr[8]=new twomodeobj(94,32,"closed","left","door1",true);
        objarr[9]=new twomodeobj(95,32,"closed","right","door1",true);
        objarr[10]=new twomodeobj(56,39,"open","top","door1",false);
        objarr[11]=new Picupobj(94,26,"src/objectspics/fireball.png");

        startgamethread();
    }
    public void startgamethread()
    {
        gamethread=new Thread(this);
        gamethread.start();
    }
    @Override
    public void run()
    {
        while (gamethread!=null)
        {
            update();


            repaint(); // the repaint redarw the update in paintComponent func

            try {
                Thread.sleep(1000/FPS);//delay so things dont move around @ super speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    public void update()
    {
        player.update();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;//cast to Graphics2D class // more funcs for 2D
        tileManager.draw(g2);

        for (int i=0;i<objarr.length;i++)
        {
            if(objarr[i]!=null)
                objarr[i].draw(g2,this);
        }

        player.draw(g2);
        g2.dispose();
    }
}
