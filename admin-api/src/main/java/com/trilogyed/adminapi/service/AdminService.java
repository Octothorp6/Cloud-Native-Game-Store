package com.trilogyed.adminapi.service;

import com.trilogyed.adminapi.model.Customer;
import com.trilogyed.adminapi.model.Product;
import com.trilogyed.adminapi.util.feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminService {

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private InvoiceClient invoiceClient;

    @Autowired
    private LevelUpClient levelUpClient;

    @Autowired
    private ProductClient productClient;

    public AdminService(){}

    public AdminService(CustomerClient customerClient, InventoryClient inventoryClient,
                        InvoiceClient invoiceClient, LevelUpClient levelUpClient, ProductClient productClient){

        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
    }

    //CRUD for Customer
    public Customer createCustomer(Customer customer){return customerClient.createCustomer(customer);}

    public Customer getCustomer(int customerId){return  customerClient.getCustomer(customerId);}

    public List<Customer> getAllCustomers(){return customerClient.getAllCustomers();}

    public void updateCustomer(Customer customer){customerClient.updateCustomer(customer);}

    public void deleteCustomer(int customerId){customerClient.deleteCustomer(customerId);}

}
