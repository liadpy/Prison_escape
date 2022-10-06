package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public Boolean uppress=false,downpress=false,leftpress=false,rightpress=false,gospeed=false;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { //when key is pressed ==true, when release==false
        int key=e.getKeyCode();

        if(key==KeyEvent.VK_W)//if pressed w
        {
            uppress=true;
        }
        if(key==KeyEvent.VK_A)//if pressed a
        {
            leftpress=true;
        }
        if(key==KeyEvent.VK_S)//if pressed s
        {
            downpress=true;
        }
        if(key==KeyEvent.VK_D)//if pressed d
        {
            rightpress=true;
        }
        if(key==KeyEvent.VK_CONTROL)//if pressed ctrl
        {
            gospeed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();
        if(key==KeyEvent.VK_W)//if pressed w
        {
            uppress=false;
        }
        if(key==KeyEvent.VK_A)//if pressed a
        {
            leftpress=false;
        }
        if(key==KeyEvent.VK_S)//if pressed s
        {
            downpress=false;
        }
        if(key==KeyEvent.VK_D)//if pressed d
        {
            rightpress=false;
        }
        if(key==KeyEvent.VK_CONTROL)//if pressed ctrl
        {
            gospeed=false;
        }
    }
}
