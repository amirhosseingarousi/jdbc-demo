package com.garousi.jdbc;

import com.garousi.jdbc.domains.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerComponent {

    public boolean tryConnection() throws SQLException {
        try(Connection connection = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "Amir92ali");) {

            boolean isValid = connection.isValid(2);
            return isValid;
        }
    }

    public List<Customer> findAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try(
            Connection connection = DriverManager
                     .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "Amir92ali");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from customers");) {


            while (rs.next()) {
                Customer customer = createCustomer(rs);
                customers.add(customer);
            }
        }
        return customers;
    }

    private Customer createCustomer(ResultSet rs) throws SQLException {
        int num = rs.getInt("customerNumber");
        String name = rs.getString("customerName");
        String conFirstName = rs.getString("contactFirstName");
        String conLastName = rs.getString("contactLastName");
        String phone = rs.getString("phone");
        String address1 = rs.getString("addressLine1");
        String address2 = rs.getString("addressLine2");
        String city = rs.getString("city");
        String state = rs.getString("state");
        String postalCode = rs.getString("postalCode");
        String country = rs.getString("country");
        int empNum = rs.getInt("salesRepEmployeeNumber");
        double creditLimit = rs.getDouble("creditLimit");

        return new Customer(num, name, conFirstName, conLastName, phone, address1, address2, city, state, postalCode, country, empNum, creditLimit);
    }
}
