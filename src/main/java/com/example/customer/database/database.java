package com.example.customer.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    public static Connection connectionDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/TicketBookingManagement";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);

            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
