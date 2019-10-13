package com.trilogyed.retailapi.service;

import com.trilogyed.retailapi.model.*;
import com.trilogyed.retailapi.util.feign.*;
import com.trilogyed.retailapi.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @RETAIL-SERVICE-LAYER
 * PURPOSE: Perform all necessary business logic for all retail purposes.
 * PRIVATE METHODS: buildInvoiceViewModel()
 * PUBLIC
 */


@Component
public class ServiceLayer {
    CustomerClient customerClient;
    InventoryClient inventoryClient;
    InvoiceClient invoiceClient;
    LevelUpClient levelUpClient;
    ProductClient productClient;

    @Autowired
    public ServiceLayer(CustomerClient customerClient, InventoryClient inventoryClient, InvoiceClient invoiceClient,
                        LevelUpClient levelUpClient, ProductClient productClient) {
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
    }


    //==============================================================================================================
    // CUSTOMER METHODS
    public Customer findCustomerById(int customerId) {
        return null;
    }

    public Customer saveCustomer() {
        return null;
    }

    public List<Customer> findAllCustomers() {
        return null;
    }

    //==============================================================================================================
    // INVENTORY METHODS
    public Inventory findInventory(int inventoryId) {
        return null;
    }

    public Inventory findInventoryByProductId(int productId) {
        return null;
    }

    public List<Inventory> findAllInventory() {
        return null;
    }

    public Inventory saveInventory(Inventory inventory) {
        return null;
    }
    //==============================================================================================================
    // INVOICE METHODS
    public InvoiceViewModel findInvoiceById(int invoiceId) {
        return null;
    }

    public List<InvoiceViewModel> findInvoicesByCustomerId(int customerId) {
        return null;
    }

    public List<InvoiceViewModel> findAllInvoices() {
        return null;
    }

    public InvoiceViewModel saveInvoice(Invoice invoice) {
        return null;
    }

    //==============================================================================================================
    // LEVEL-UP METHODS
    public LevelUp findLevelUp(int levelUpId) {
        return null;
    }

    public LevelUp findLevelUpByCustomer(int customerId) {
        return null;
    }

    public List<LevelUp> findAllLevelUps() {
        return null;
    }

    //==============================================================================================================
    // PRODUCT METHODS
    public Product findProduct(int productId) {
        return null;
    }

    public Product findProductByProductName(String productName) {
        return null;
    }

    public List<Product> findAllProducts() {
        return null;
    }


    // FALL-BACK METHOD
    public LevelUp reliable() {
        LevelUp levelUp = new LevelUp();
        return levelUp;
    }

}
