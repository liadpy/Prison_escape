package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable  {
    final int origianltilesize=16;//16x16 pixels
    final int scale=3;
    public final int tilesize=origianltilesize*scale;//16x16 pix is very small so i make it bigger

    final int scrnrow=24;//12 tiles on vertical
    final int scrncol=26;//16 tiles on horizontal
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
    TileManager tileManager=new TileManager(this);
    public CollisionDetector collisionDetector=new CollisionDetector(this);

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(scrnwidth,scrnhight));//giveing the panel it's size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);//improve performance
        this.addKeyListener(keyhandler);
        this.setFocusable(true);//gamepanel can receive key input
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

    public void paintComponent(Graphics g)//after the
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;//cast to Graphics2D class // more funcs for 2D
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
