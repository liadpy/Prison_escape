package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        //new Launchgame();
        ImageIcon imageIcon = new ImageIcon("");

        JFrame frame=new JFrame();
        JButton gamebtn = new JButton("Start Game");
        gamebtn.setBounds(200,100,200,100);
        JButton leadbtn = new JButton("See Leader board");
        leadbtn.setBounds(200,350,200,100);
        JTextField textField=new JTextField();
        JLabel label=new JLabel("enter name : ");
        label.setBounds(200,220,200,25);
        textField.setBounds(200,250,200,50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Escape The Town");
        ImageIcon backgroundpic = new ImageIcon("src/playersprites/menubg.png");
        Image bg=backgroundpic.getImage();
        Image bgscale = bg.getScaledInstance(600,600,Image.SCALE_SMOOTH);//resize pic
        ImageIcon bgc=new ImageIcon(bgscale);
        JLabel bglbl = new JLabel();
        bglbl.setBounds(0, 0, 600, 600);
        bglbl.setIcon(bgc);

        frame.pack();//sets window size to be prefered size==gamepanel

        frame.setLocationRelativeTo(null);//sets the window to be at the center of the screen
        frame.setLayout(null);
        frame.setSize(600,600);
        frame.add(bglbl);
        bglbl.add(gamebtn);
        bglbl.add(leadbtn);
        bglbl.add(textField);
        bglbl.add(label);
        frame.setVisible(true);

        gamebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inpt= textField.getText();
                if(!Objects.equals(inpt, ""))//making sure inpt not null
                {
                    new Launchgame(inpt);
                    frame.dispose();
                }
            }
        });
        leadbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO leaderboard page
            }
        });
    }
}
