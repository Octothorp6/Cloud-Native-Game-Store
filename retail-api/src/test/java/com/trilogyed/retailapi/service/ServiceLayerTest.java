package com.trilogyed.retailapi.service;

import com.trilogyed.retailapi.util.feign.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceLayerTest {

    CustomerClient customerClient;
    LevelUpClient levelUpClient;
    InventoryClient inventoryClient;
    InvoiceClient invoiceClient;
    ProductClient productClient;
    ServiceLayer serviceLayer;


    @Before
    public void setUp() throws Exception {
        setUpCustomerMock();
        setUpInventoryMock();
        setUpInvoiceMock();
        setUpLevelUpMock();
        setUpProductMock();

        serviceLayer = new ServiceLayer(customerClient, inventoryClient, invoiceClient,levelUpClient,productClient);
    }

    @Test
    public void findCustomerById() {
    }

    @Test
    public void saveCustomer() {
    }

    @Test
    public void findAllCustomers() {
    }

    @Test
    public void findInventory() {
    }

    @Test
    public void findInventoryByProductId() {
    }

    @Test
    public void findAllInventory() {
    }

    @Test
    public void saveInventory() {
    }

    @Test
    public void findInvoiceById() {
    }

    @Test
    public void findInvoicesByCustomerId() {
    }

    @Test
    public void findAllInvoices() {
    }

    @Test
    public void saveInvoice() {
    }

    @Test
    public void findLevelUp() {
    }

    @Test
    public void findLevelUpByCustomer() {
    }

    @Test
    public void findAllLevelUps() {
    }

    @Test
    public void findProduct() {
    }

    @Test
    public void findProductByProductName() {
    }

    @Test
    public void findAllProducts() {
    }


    //=====================================================================================================
    // SET UP MOCK INFORMATION

    private void setUpCustomerMock() {

    }

    private void setUpInventoryMock() {

    }

    private void setUpInvoiceMock() {

    }

    private void setUpLevelUpMock() {

    }

    private void setUpProductMock() {

    }
}