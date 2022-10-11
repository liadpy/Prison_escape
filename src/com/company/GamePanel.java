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

    Key1 key1_1=new Key1(58,53);
    twomodeobj firsttowndoor=new twomodeobj(79,50,"open","top","door1");
    twomodeobj secondtowndoor=new twomodeobj(79,61,"open","top","door1");
    twomodeobj thirdtowndoor=new twomodeobj(79,68,"open","bottom","door1");
    twomodeobj fourthtowndoor1=new twomodeobj(91,58,"open","left","door1");
    twomodeobj fourthtowndoor2=new twomodeobj(92,58,"closed","right","door1");
    twomodeobj fifthtowndoor=new twomodeobj(108,60,"closed","bottom","door1");
    twomodeobj labdoor=new twomodeobj(88,66,"closed","bottom","door1");
    twomodeobj laketowndoor1=new twomodeobj(94,32,"closed","left","door1");
    twomodeobj laketowndoor2=new twomodeobj(95,32,"closed","right","door1");

    twomodeobj doormyhouse=new twomodeobj(56,39,"open","top","door1");

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

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;//cast to Graphics2D class // more funcs for 2D
        tileManager.draw(g2);

        key1_1.draw(g2,this);//objs
        doormyhouse.draw(g2,this);
        firsttowndoor.draw(g2,this);
        secondtowndoor.draw(g2,this);
        thirdtowndoor.draw(g2,this);
        fourthtowndoor1.draw(g2,this);
        fourthtowndoor2.draw(g2,this);
        fifthtowndoor.draw(g2,this);
        labdoor.draw(g2,this);
        laketowndoor1.draw(g2,this);
        laketowndoor2.draw(g2,this);

        player.draw(g2);
        g2.dispose();
    }
}
