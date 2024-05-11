package com.garousi.jdbc;

import com.garousi.jdbc.domains.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeComponent {

    public boolean tryConnection() throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
        boolean isValid = connection.isValid(2);
        connection.close();
        return isValid;
    }

    public List<Employee> findAll() throws SQLException {
        String query = "select * from employees";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee employee = createEmployee(rs);
                employees.add(employee);
            }
        }
        return employees;
    }

    public Employee findByNumber(Integer number) throws SQLException {
        String query = "select * from employees where employeeNumber = ?";
        Employee employee = null;

        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, number);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    employee = createEmployee(rs);
                }
            }
        }
        return employee;
    }

    public List<Employee> findByOffice(String code) throws SQLException {
        String query = "select * from employees where officeCode = ?";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, code);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee emp = createEmployee(rs);
                    employees.add(emp);
                }
            }
        }
        return employees;
    }

    private Employee createEmployee(ResultSet rs) throws SQLException {
        int id = rs.getInt("employeeNumber");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String extension = rs.getString("extension");
        String email = rs.getString("email");
        String officeCode = rs.getString("officeCode");
        int reportsTo = rs.getInt("reportsTo");
        String jobTitle = rs.getString("jobTitle");

        return new Employee(
                id, firstName, lastName, extension,
                email, officeCode, reportsTo, jobTitle
        );
    }
}
