package com.trilogyed.retailapi.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.trilogyed.retailapi.model.*;
import com.trilogyed.retailapi.util.feign.*;
import com.trilogyed.retailapi.util.messages.LevelUpEntry;
import com.trilogyed.retailapi.viewmodel.InvoiceViewModel;
import com.trilogyed.retailapi.viewmodel.OrderViewModel;
import org.apache.tomcat.jni.Error;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.time.LocalDate;
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

    public OrderViewModel submitInvoice(Order order) {
        // CUSTOMER
        Customer customer = mapOrderToCustomer(order);
        customer = saveCustomer(customer);

        // DEFINE PRODUCT-INVENTORY-INVOICE ITEMS
        Product product = findProductByProductName(order.getProductName());
        Inventory inventory = findInventoryByProductId(product.getProductId());


        // INVOICE
        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setPurchaseDate(LocalDate.now());
        InvoiceViewModel invoiceViewModel = saveInvoice(invoice);



        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInventoryId(inventory.getInventoryId());
        invoiceItem.setInvoiceId(invoiceViewModel.getId());
        invoiceItem.setQuantity(order.getQuantity());
        invoiceItem.setUnitPrice(product.getUnitCost());
        invoiceClient.createInvoiceItem(invoiceItem);

        // SET POINTS AND STORE ENTRY
        int totalToInt = invoiceViewModel.getTotal().intValue();
        int points = calculatePointTotal(totalToInt);

        LevelUpEntry levelUp = new LevelUpEntry();
        levelUp.setCustomerId(customer.getCustomerId());
        levelUp.setMemberDate(invoice.getPurchaseDate());
        levelUp.setPoints(points);
        saveLevelUp(levelUp);

        order.setInvoiceId(invoiceViewModel.getId());

        return buildOrderViewModel(order);
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

    public void saveLevelUp(@Valid LevelUpEntry levelUpEntry) {
        System.out.println("sending message..." + levelUpEntry.toString());
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, levelUpEntry);
        System.out.println("message sent.");
    }

    // FALL-BACK METHOD
    public LevelUp reliable() {
        return new LevelUp();
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


    public List<Product> findProductsByInvoiceId(int id) {
        List<Product> products = new ArrayList<>();
        InvoiceViewModel invoiceViewModel = findInvoiceById(id);

        if (invoiceViewModel == null) {
            return null;
        }

        for(InvoiceItem i : invoiceViewModel.getInvoiceItems()) {
            Inventory inventory = findInventory(i.getInventoryId());
            Product product = findProduct(inventory.getProductId());
            products.add(product);
        }
        return products;
    }

    //==============================================================================================================
    // HELPER METHODS

    private OrderViewModel buildOrderViewModel(Order order) {
        OrderViewModel orderViewModel = new OrderViewModel();
        // RETRIEVE ALL VALUES
        Customer customer = findCustomerById(order.getCustomerId());
        LevelUp levelUp = findLevelUpByCustomer(order.getCustomerId());
        InvoiceViewModel invoice = findInvoiceById(order.getInvoiceId());

        // STORE VALUES
        orderViewModel.setCustomer(customer);
        orderViewModel.setLevelUpPoints(levelUp.getPoints());
        orderViewModel.setInvoice(invoice);
        orderViewModel.setPurchaseDate(levelUp.getMemberDate());

        return orderViewModel;
    }

    private boolean validateInventory(Inventory inventory, int orderQuantity) {
        boolean inStock = false;

        if (inventory.getQuantity() > orderQuantity) {
            inStock = true;
        } else {
            throw new IllegalArgumentException("Sorry we do not have enough in stock for that item.");
        }

        return inStock;
    }

    private int calculatePointTotal(int total) {
        int minimum = 50;
        int result;
        int pointTotal;
        // if less than 50, return 0 points
        if (total < minimum) {
            return 0;
        }
        // otherwise, return the point total value
        result = total / minimum;
        pointTotal = result * 10;

        return pointTotal;
    }


    private static Customer mapOrderToCustomer(Order order) {
        Customer customer = new Customer();
        customer.setFirstName(order.getFirstName());
        customer.setLastName(order.getLastName());
        customer.setStreet(order.getStreet());
        customer.setCity(order.getCity());
        customer.setZip(order.getZip());
        customer.setEmail(order.getEmail());
        customer.setPhone(order.getPhone());

        return customer;
    }
}
