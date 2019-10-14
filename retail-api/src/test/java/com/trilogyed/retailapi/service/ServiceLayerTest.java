package com.trilogyed.retailapi.service;

import com.trilogyed.retailapi.model.Customer;
import com.trilogyed.retailapi.model.Inventory;
import com.trilogyed.retailapi.model.LevelUp;
import com.trilogyed.retailapi.model.Product;
import com.trilogyed.retailapi.util.feign.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ServiceLayerTest {

    CustomerClient customerClient;
    LevelUpClient levelUpClient;
    InventoryClient inventoryClient;
    InvoiceClient invoiceClient;
    ProductClient productClient;
    ServiceLayer serviceLayer;
    RabbitTemplate rabbitTemplate;

    @Before
    public void setUp() throws Exception {
        setUpCustomerMock();
        setUpInventoryMock();
        setUpInvoiceMock();
        setUpLevelUpMock();
        setUpProductMock();
        serviceLayer = new ServiceLayer(customerClient, inventoryClient, invoiceClient,
                levelUpClient, productClient, rabbitTemplate);
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

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Neo");
        customer.setLastName("The One");
        customer.setStreet("230 wabash");
        customer.setCity("The Matrix");
        customer.setZip("11111");
        customer.setEmail("neo@matrix.com");
        customer.setPhone("091144564");

        Customer customerA = new Customer();
        customerA.setFirstName("Neo");
        customerA.setLastName("The One");
        customerA.setStreet("230 wabash");
        customerA.setCity("The Matrix");
        customerA.setZip("11111");
        customerA.setEmail("neo@matrix.com");
        customerA.setPhone("091144564");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        doReturn(customer).when(customerClient).createCustomer(customerA);
        doReturn(customer).when(customerClient).getCustomer(customer.getCustomerId());
        doReturn(customers).when(customerClient).getAllCustomers();
    }

    private void setUpInventoryMock() {
        inventoryClient = mock(InventoryClient.class);

        Inventory inventory = new Inventory();
        inventory.setInventoryId(1);
        inventory.setProductId(5);
        inventory.setQuantity(10);

        Inventory toUpdate = new Inventory();
        toUpdate.setInventoryId(2);
        toUpdate.setProductId(15);
        toUpdate.setQuantity(30);

        Inventory updated = new Inventory();
        updated.setInventoryId(2);
        updated.setProductId(15);
        updated.setQuantity(15);

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory);

        doReturn(inventory).when(inventoryClient).getInventory(inventory.getInventoryId());
        doReturn(inventory).when(inventoryClient).getInventoryByProduct(inventory.getProductId());
        doReturn(updated).when(inventoryClient).getInventory(toUpdate.getInventoryId());
        doReturn(inventoryList).when(inventoryClient).getAllInventory();
        doNothing().when(inventoryClient).updateInventory(toUpdate);
    }

    private void setUpInvoiceMock() {
        invoiceClient = mock(InvoiceClient.class);

    }

    private void setUpLevelUpMock() {
        levelUpClient = mock(LevelUpClient.class);

        LevelUp levelUp = new LevelUp();
        levelUp.setLevelUpId(1);
        levelUp.setCustomerId(5);
        levelUp.setPoints(100);
        levelUp.setMemberDate(LocalDate.of(2019,11,10));

        LevelUp levelUp2 = new LevelUp();
        levelUp2.setLevelUpId(5);
        levelUp2.setCustomerId(10);
        levelUp2.setPoints(300);
        levelUp2.setMemberDate(LocalDate.of(2019,11,10));

        doReturn(levelUp).when(levelUpClient).getLevelUp(levelUp.getLevelUpId());
        doReturn(levelUp).when(levelUpClient).getLevelUpByCustomer(levelUp2.getCustomerId());

    }

    private void setUpProductMock() {
        productClient = mock(ProductClient.class);

        Product productA = new Product();
        productA.setProductId(1);
        productA.setName("Xbox Scorpion");
        productA.setDescription("Microsoft's latest console");
        productA.setListPrice(new BigDecimal("599.99"));
        productA.setUnitCost(new BigDecimal("550.99"));

        Product productB = new Product();
        productB.setProductId(2);
        productB.setName("Call of Duty: Modern Warfare");
        productB.setDescription("Activision's newest addition, coming oct 22");
        productB.setListPrice(new BigDecimal("59.99"));
        productB.setUnitCost(new BigDecimal("50.99"));


        List<Product> productList = new ArrayList<>();
        productList.add(productA);
        productList.add(productB);

        doReturn(productA).when(productClient).getProductByName("Xbox Scorpion");
        doReturn(productA).when(productClient).getProduct(productA.getProductId());
        doReturn(productList).when(productClient).getAllProducts();
    }
}