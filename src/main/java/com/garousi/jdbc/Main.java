package com.garousi.jdbc;

import com.garousi.jdbc.domains.Customer;
import com.garousi.jdbc.domains.Employee;
import com.garousi.jdbc.domains.Product;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProductComponent productComponent = new ProductComponent();
        CustomerComponent customerComponent = new CustomerComponent();
        EmployeeComponent employeeComponent = new EmployeeComponent();

        try {
            List<Product> products = null;
            List<Customer> customers = null;

            List<Employee> employees =
                    employeeComponent.findByOffice(String.valueOf(1));
            System.out.println(employees.size());
//            Employee emp = employeeComponent.findByNumber(1504);
//            System.out.println(emp);
//            List<Employee> employees = employeeComponent.findAll();
//            System.out.println(employees.size());
//            customers = customerComponent.findAll();
//            System.out.println(customers.size());
//            products = productComponent.findAll();
//            products = productComponent.findProductByPrice(50.00, 55.0);
//            Product product = productComponent.findByCode("S18_1589");
//            System.out.println(products.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
