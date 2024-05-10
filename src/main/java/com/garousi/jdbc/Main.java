package com.garousi.jdbc;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        ProductComponent productComponent = new ProductComponent();
        try {
            if(productComponent.tryConnection()) {
                System.out.println("Try to connect with DriverManager");
                System.out.println("****  SUCCESS  ****");

                productComponent.printProductList();
            } else {
                System.out.println("Try to connect with DriverManager");
                System.out.println("FAILED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
