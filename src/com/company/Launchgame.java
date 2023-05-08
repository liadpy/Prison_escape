package com.company;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Launchgame {
    public Launchgame(String username) {
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setResizable(false);
        window.setTitle("Escape The Town");

        GamePanel gamePanel=new GamePanel(username);
        window.add(gamePanel);
        window.pack();//sets window size to be prefered size==gamepanel

        window.setLocationRelativeTo(null);//sets the window to be at the center of the screen
        window.setVisible(true);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {  // triggers when user exits and saves user's record
                Alldb db =new Alldb();
                db.insert_to_db(gamePanel.username,gamePanel.player.teslas_killed_c);
            }
        });


    }

}
