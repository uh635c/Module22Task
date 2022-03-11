package com.uh635c.task22.repository;

import java.sql.*;

public class ConnectionDB {
    private volatile static Connection connection;

    private ConnectionDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/books_lib",
                    "root",
                    "Mysql_pass_00");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        if( connection == null){
            synchronized (ConnectionDB.class){
                if(connection == null){
                    new ConnectionDB();
                }
            }
        }
        return connection.prepareStatement(sql);
    }
}
