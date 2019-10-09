package com.trilogyed.customerservice.dao;

import com.trilogyed.customerservice.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {

    





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
}
