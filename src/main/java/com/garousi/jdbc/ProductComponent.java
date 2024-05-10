package com.garousi.jdbc;

import java.sql.*;

public class ProductComponent {

    public boolean tryConnection() throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
        boolean isValid = connection.isValid(2);
        connection.close();
        return isValid;
    }

    public void printProductList() throws SQLException {
        try (
                Connection connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from products");
        ) {

            while (rs.next()) {
                String name = rs.getString("productName");
                System.out.println(name);
            }
        }
    }
}
