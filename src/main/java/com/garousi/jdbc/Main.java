package com.garousi.jdbc;

import com.garousi.jdbc.domains.Product;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProductComponent productComponent = new ProductComponent();
        try {
            if(productComponent.tryConnection()) {
                System.out.println("Try to connect with DriverManager");
                System.out.println("****  SUCCESS  ****");

                List<Product> products = productComponent.findAll();
                System.out.println(products.size());
            } else {
                System.out.println("Try to connect with DriverManager");
                System.out.println("FAILED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
