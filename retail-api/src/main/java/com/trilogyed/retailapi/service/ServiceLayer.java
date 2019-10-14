package com.trilogyed.retailapi.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.trilogyed.retailapi.model.*;
import com.trilogyed.retailapi.util.feign.*;
import com.trilogyed.retailapi.util.helper.Helper;
import com.trilogyed.retailapi.util.messages.LevelUpEntry;
import com.trilogyed.retailapi.viewmodel.InvoiceViewModel;
import com.trilogyed.retailapi.viewmodel.OrderViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @RETAIL-SERVICE-LAYER
 * PURPOSE: Perform all necessary business logic for all retail purposes.
 * PRIVATE METHODS: buildOrderViewModel()
 * PUBLIC METHODS:
 */

@Component
public class ServiceLayer {
    CustomerClient customerClient;
    InventoryClient inventoryClient;
    InvoiceClient invoiceClient;
    LevelUpClient levelUpClient;
    ProductClient productClient;
    RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE = "level-up-exchange";
    public static final String ROUTING_KEY = "level.up.create.level";


    @Autowired
    public ServiceLayer(CustomerClient customerClient, InventoryClient inventoryClient, InvoiceClient invoiceClient,
                        LevelUpClient levelUpClient, ProductClient productClient, RabbitTemplate rabbitTemplate ) {
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    //==============================================================================================================
    // CUSTOMER METHODS

    public Customer findCustomerById(int customerId) {
        return customerClient.getCustomer(customerId);
    }

    public Customer saveCustomer(Customer customer) {
        return customerClient.createCustomer(customer);
    }

    public List<Customer> findAllCustomers() {
        return customerClient.getAllCustomers();
    }

    //==============================================================================================================
    // INVENTORY METHODS

    public Inventory findInventory(int inventoryId) {
        return inventoryClient.getInventory(inventoryId);
    }

    public Inventory findInventoryByProductId(int productId) {
        return inventoryClient.getInventoryByProduct(productId);
    }

    public List<Inventory> findAllInventory() {
        return inventoryClient.getAllInventory();
    }

    //==============================================================================================================
    // INVOICE METHODS

    public InvoiceViewModel findInvoiceById(int invoiceId) {
        return invoiceClient.getInvoiceById(invoiceId);
    }

    public List<InvoiceViewModel> findInvoicesByCustomerId(int customerId) {
        return invoiceClient.getAllInvoicesByCustomer(customerId);
    }

    public InvoiceViewModel saveInvoice(Invoice invoice) {
        return invoiceClient.createInvoice(invoice);
    }

    //==============================================================================================================
    // LEVEL-UP METHODS

    @HystrixCommand(fallbackMethod = "reliable")
    public LevelUp findLevelUp(int levelUpId) {
        return levelUpClient.getLevelUp(levelUpId);
    }

    public LevelUp findLevelUpByCustomer(int customerId) {
        return levelUpClient.getLevelUpByCustomer(customerId);
    }

    public void createLevelUp(@Valid LevelUpEntry levelUpEntry) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, levelUpEntry);
    }

    //==============================================================================================================
    // PRODUCT METHODS
    public Product findProduct(int productId) {
        return productClient.getProduct(productId);
    }

    public Product findProductByProductName(String productName) {
        return productClient.getProductByName(productName);
    }

    public List<Product> findAllProducts() {
        return productClient.getAllProducts();
    }

    public List<Product> findProductsInInventory() {
        List<Inventory> inventoryList = findAllInventory();
        List<Product> inStock = new ArrayList<>();

        for (Inventory i : inventoryList) {
            if (i.getQuantity() > 0) {
                Product product = findProduct(i.getProductId());
                inStock.add(product);
            }
        }

        return inStock;
    }

    // FALL-BACK METHOD
    public LevelUp reliable() {
        LevelUp levelUp = new LevelUp();
        return levelUp;
    }


    // BUILD-VIEW-MODEL
    private OrderViewModel buildOrderViewModel(Order order) {
        OrderViewModel orderViewModel = new OrderViewModel();
        Customer customer = new Customer();
        LevelUp levelUp = new LevelUp();



        return orderViewModel;
    }
}
