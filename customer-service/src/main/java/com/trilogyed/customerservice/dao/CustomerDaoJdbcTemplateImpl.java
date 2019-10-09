package com.trilogyed.customerservice.dao;

import com.trilogyed.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    //Prepared Statements

    private static final String INSERT_CUSTOMER_SQL =
            "INSERT INTO customer (first_name, last_name, street, city, zip, email, phone) values(?,?,?,?,?,?,?)";

    private static final String SELECT_CUSTOMER_SQL =
            "SELECT * FROM customer WHERE customer_id = ?";

    private static final String SELECT_ALL_CUSTOMERS_SQL =
            "SELECT * FROM customer";

    private static final String UPDATE_CUSTOMER_SQL =
            "UPDATE customer set first_name = ?, last_name = ?, street = ?, city = ?, zip = ?, email = ?, phone = ? WHERE customer_id = ?";

    private static final String DELETE_CUSTOMER_SQL =
            "DELETE FROM customer WHERE customer_id =?";

    @Autowired
    public CustomerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}



    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer getCustomer(int customerId) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(int customerId) {

    }

    private Customer mapRowToCustomer
}
