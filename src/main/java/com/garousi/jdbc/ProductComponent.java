package com.garousi.jdbc;

import com.garousi.jdbc.domains.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductComponent {

    public boolean tryConnection() throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "Amir92ali");
        boolean isValid = connection.isValid(2);
        connection.close();
        return isValid;
    }

    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (
                Connection connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "Amir92ali");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from products");
        ) {

            while (rs.next()) {
                Product product = createProduct(rs);
                products.add(product);
            }
        }
        return products;
    }

    public List<Product> findProductByPrice(Double lowPrice, Double highPrice) throws SQLException {
        String query = "select * from products " +
                " where buyPrice between ? and ?";

        List<Product> products = new ArrayList<>();
        try(
             Connection connection = DriverManager
                     .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "Amir92ali");
             PreparedStatement ps = connection.prepareStatement(query);) {

            ps.setDouble(1, lowPrice);
            ps.setDouble(2, highPrice);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = createProduct(rs);
                    products.add(product);
                }
            }
        }
        return products;
    }

    public Product findByCode(String code) throws SQLException {
        String query = "select * from products where productCode = ?";
        Product product = null;
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "Amir92ali");
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, code);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    product = createProduct(rs);
                }
            }
        }
        return product;
    }

    private Product createProduct(ResultSet rs) throws SQLException {
        String code = rs.getString("productCode");
        String name = rs.getString("productName");
        String line = rs.getString("productLine");
        String scale = rs.getString("productScale");
        String vendor = rs.getString("productVendor");
        String desc = rs.getString("productDescription");
        Integer quantity = rs.getInt("quantityInStock");
        Double price = rs.getDouble("buyPrice");
        Double msrp = rs.getDouble("MSRP");

        return new Product(code, name, line, scale, vendor, desc, quantity, price, msrp);
    }



}
