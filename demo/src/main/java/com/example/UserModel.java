package com.example;

import java.sql.*;

public class UserModel {
    private Connection conn;

    public UserModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM user_details WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getRole(String username) {
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT role FROM user_details WHERE username='" + username + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public String getName(String username) {
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT name FROM user_details WHERE username='" + username + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public User getUser(String username, String password) {
        User user = null;
        String query = "SELECT * FROM user_details WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    
}