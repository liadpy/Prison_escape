package com.company;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.*;
public class Alldb {
    public Alldb() {

    }

    public void insert_to_db(String name , int kills) {
        PreparedStatement statement = null;
        Connection conn=null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-esc_town", "root", "meshpuk2133meshpuk2133");
            String query = "INSERT INTO leader_board (name, kills) VALUES (?, ?)";
            statement = conn.prepareStatement(query);
            statement.setString(1,name);
            statement.setInt(2,kills);
            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement!=null)
                    statement.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    }

