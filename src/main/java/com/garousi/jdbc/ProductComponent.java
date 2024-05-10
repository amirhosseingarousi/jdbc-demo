package com.garousi.jdbc;

import com.garousi.jdbc.domains.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductComponent {

    public boolean tryConnection() throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
        boolean isValid = connection.isValid(2);
        connection.close();
        return isValid;
    }

    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();

        try (
                Connection connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from products");
        ) {

            while (rs.next()) {
                String code = rs.getString("productCode");
                String name = rs.getString("productName");
                String line = rs.getString("productLine");
                String scale = rs.getString("productScale");
                String vendor = rs.getString("productVendor");
                String desc = rs.getString("productDescription");
                Integer quantity = rs.getInt("quantityInStock");
                Double price = rs.getDouble("buyPrice");
                Double msrp = rs.getDouble("MSRP");

                Product product = new Product();
                product = Product.createProduct(code, name, line, scale, vendor, desc, quantity, price, msrp);
                products.add(product);
            }
        }
        return products;
    }


}
