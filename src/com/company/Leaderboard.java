package com.company;

import java.sql.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Leaderboard {

    public Leaderboard() {
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Escape The Town - leader board");
        frame.pack();//sets window size to be prefered size==gamepanel

        frame.setLocationRelativeTo(null);//sets the window to be at the center of the screen
        frame.setLayout(null);
        frame.setSize(600,600);
        ImageIcon backgroundpic = new ImageIcon("src/playersprites/menubg.png");
        Image bg=backgroundpic.getImage();
        Image bgscale = bg.getScaledInstance(600,600,Image.SCALE_SMOOTH);//resize pic
        ImageIcon bgc=new ImageIcon(bgscale);
        JLabel bglbl = new JLabel();
        bglbl.setBounds(0, 0, 600, 600);
        bglbl.setIcon(bgc);



        Statement stmt=null;
        Connection conn=null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-esc_town", "root", "meshpuk2133meshpuk2133");
            String query = "SELECT * FROM leader_board ORDER BY kills DESC";
            stmt =conn.createStatement();
            ResultSet rs =stmt.executeQuery(query);
            int i=0;

            while (i<10&&rs.next()){
                String name = rs.getString("name");
                int kills = rs.getInt("kills");
                String txtforlbl="Name: " + name + ", Killed  : " + kills;
                JLabel lbl=new JLabel(txtforlbl);
                lbl.setBounds(20,50+i*50,560,50);
                Font font = new Font(lbl.getFont().getName(), Font.BOLD, 20);
                lbl.setFont(font);
                bglbl.add(lbl);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
            if (conn!=null) {

                    conn.close();

            }
            if(stmt!=null)
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            frame.add(bglbl);
        }





        frame.setVisible(true);



    }
}
