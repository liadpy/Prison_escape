package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setResizable(false);
        window.setTitle("Prison Escape");

        GamePanel gamePanel=new GamePanel();
        window.add(gamePanel);
        window.pack();//sets window size to be prefered size==gamepanel

        window.setLocationRelativeTo(null);//sets the window to be at the center of the screen
        window.setVisible(true);

    }
}
