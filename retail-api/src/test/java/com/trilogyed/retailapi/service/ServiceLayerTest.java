package com.trilogyed.retailapi.service;

import com.trilogyed.retailapi.util.feign.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


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

    // ========================================================================================
    // CUSTOMER METHODS
    @Test
    public void findCustomerById() {
    }

    @Test
    public void saveCustomer() {
    }

    @Test
    public void findAllCustomers() {
    }

    // ========================================================================================
    // INVENTORY METHODS
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

    // ========================================================================================
    // INVOICE METHODS
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

    // ========================================================================================
    // LEVEL UP METHODS
    @Test
    public void findLevelUp() {
    }

    @Test
    public void findLevelUpByCustomer() {
    }

    @Test
    public void findAllLevelUps() {
    }

    // ========================================================================================
    // PRODUCT METHODS

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
        customerClient = mock(CustomerClient.class);

    }

    private void setUpInventoryMock() {
        inventoryClient = mock(InventoryClient.class);
    }

    private void setUpInvoiceMock() {
        invoiceClient = mock(InvoiceClient.class);
    }

    private void setUpLevelUpMock() {
        levelUpClient = mock(LevelUpClient.class);
    }

    private void setUpProductMock() {
        productClient = mock(ProductClient.class);
    }
}