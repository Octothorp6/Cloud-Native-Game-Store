package com.trilogyed.adminapi.service;

import com.trilogyed.adminapi.invoiceViewmodel.InvoiceViewModel;
import com.trilogyed.adminapi.model.*;
import com.trilogyed.adminapi.service.AdminService;
import com.trilogyed.adminapi.util.feign.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AdminServiceLayerTest {

    private AdminService adminService;

    private CustomerClient customerClient;
    private InventoryClient inventoryClient;
    private InvoiceClient invoiceClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;

    @Before
    public void setUp() throws Exception{
        customerMockSetUp();
        inventoryMockSetUp();
        invoiceMockSetUp();
        levelUpMockSetUp();
        productMockSetUp();

        adminService = new AdminService(customerClient,inventoryClient,invoiceClient,levelUpClient,productClient);
    }

    private void customerMockSetUp(){
        customerClient = mock(CustomerClient.class);

        Customer custA = new Customer();
        custA.setCustomerId(1);
        custA.setFirstName("Neo");
        custA.setLastName("The One");
        custA.setStreet("230 wabash");
        custA.setCity("The Matrix");
        custA.setZip("11111");
        custA.setEmail("neo@matrix.com");
        custA.setPhone("091144564");

        Customer customerB = new Customer();
        customerB.setFirstName("Neo");
        customerB.setLastName("The One");
        customerB.setStreet("230 wabash");
        customerB.setCity("The Matrix");
        customerB.setZip("11111");
        customerB.setEmail("neo@matrix.com");
        customerB.setPhone("091144564");

        doReturn(custA).when(customerClient).createCustomer(customerB);
        doReturn(custA).when(customerClient).getCustomer(custA.getCustomerId());
        //============================================================

        Customer custC = new Customer();
        custC.setCustomerId(2);
        custC.setFirstName("Trinity");
        custC.setLastName("T");
        custC.setStreet("230 wabash");
        custC.setCity("The Matrix");
        custC.setZip("11111");
        custC.setEmail("trinity@matrix.com");
        custC.setPhone("091144564");

        Customer customerD = new Customer();
        customerD.setFirstName("Trinity");
        customerD.setLastName("T");
        customerD.setStreet("230 wabash");
        customerD.setCity("The Matrix");
        customerD.setZip("11111");
        customerD.setEmail("trinity@matrix.com");
        customerD.setPhone("091144564");

        doReturn(custC).when(customerClient).createCustomer(customerD);
        doReturn(custC).when(customerClient).getCustomer(custC.getCustomerId());

        //============================================================
        //List of Customers size 2
        List<Customer> customerList = new ArrayList<>();
        customerList.add(custA);
        customerList.add(custC);

        doReturn(customerList).when(customerClient).getAllCustomers();

        //============================================================
        //Update Obj

        Customer customerNoId = new Customer();
        customerNoId.setFirstName("Morpheus");
        customerNoId.setLastName("The Prophet");
        customerNoId.setStreet("231 wabash");
        customerNoId.setCity("The Matrix");
        customerNoId.setZip("11112");
        customerNoId.setEmail("morpheus@matrix.com");
        customerNoId.setPhone("091144564");

        Customer customerNotUpdatedId = new Customer();
        customerNotUpdatedId.setCustomerId(3);
        customerNotUpdatedId.setFirstName("Morpheus");
        customerNotUpdatedId.setLastName("The Prophet");
        customerNotUpdatedId.setStreet("231 wabash");
        customerNotUpdatedId.setCity("The Matrix");
        customerNotUpdatedId.setZip("11112");
        customerNotUpdatedId.setEmail("morpheus@matrix.com");
        customerNotUpdatedId.setPhone("091144564");

        Customer customerUpdatedId = new Customer();
        customerUpdatedId.setCustomerId(3);
        customerUpdatedId.setFirstName("Morpheus");
        customerUpdatedId.setLastName("The Prophet");
        customerUpdatedId.setStreet("240 wabash");
        customerUpdatedId.setCity("The Matrix");
        customerUpdatedId.setZip("11112");
        customerUpdatedId.setEmail("morpheus@zion.org");
        customerUpdatedId.setPhone("091144569");

        doReturn(customerNotUpdatedId).when(customerClient).createCustomer(customerNoId);
        doNothing().when(customerClient).updateCustomer(customerUpdatedId);
        doReturn(customerUpdatedId).when(customerClient).getCustomer(customerUpdatedId.getCustomerId());

        //============================================================
    }
    @Test
    public void createGetGetAllCustomers(){
        Customer customer = new Customer();
        customer.setFirstName("Neo");
        customer.setLastName("The One");
        customer.setStreet("230 wabash");
        customer.setCity("The Matrix");
        customer.setZip("11111");
        customer.setEmail("neo@matrix.com");
        customer.setPhone("091144564");

        customer = adminService.createCustomer(customer);

        Customer customerFromAdmin = adminService.getCustomer(customer.getCustomerId());
        assertEquals(customerFromAdmin, customer);

        Customer customer2 = new Customer();
        customer2.setFirstName("Trinity");
        customer2.setLastName("T");
        customer2.setStreet("230 wabash");
        customer2.setCity("The Matrix");
        customer2.setZip("11111");
        customer2.setEmail("trinity@matrix.com");
        customer2.setPhone("091144564");

        customer2 = adminService.createCustomer(customer2);

        List<Customer> customerList = adminService.getAllCustomers();
        assertEquals(2, customerList.size());
    }

    @Test
    public void updateCustomer(){
        Customer customerToUpdate = new Customer();
        customerToUpdate.setFirstName("Morpheus");
        customerToUpdate.setLastName("The Prophet");
        customerToUpdate.setStreet("231 wabash");
        customerToUpdate.setCity("The Matrix");
        customerToUpdate.setZip("11112");
        customerToUpdate.setEmail("morpheus@matrix.com");
        customerToUpdate.setPhone("091144564");

        customerToUpdate = adminService.createCustomer(customerToUpdate);

        customerToUpdate.setStreet("240 wabash");
        customerToUpdate.setEmail("morpheus@zion.org");
        customerToUpdate.setPhone("091144569");

        adminService.updateCustomer(customerToUpdate);

        Customer customerUpdated = adminService.getCustomer(customerToUpdate.getCustomerId());

        assertEquals(customerUpdated,customerToUpdate);
    }
    //================================================================================================================



    private void inventoryMockSetUp(){
        inventoryClient = mock(InventoryClient.class);

        Inventory inventory = new Inventory();
        inventory.setInventoryId(1);
        inventory.setProductId(5);
        inventory.setQuantity(10);

        Inventory inventory1 = new Inventory();
        inventory1.setProductId(5);
        inventory1.setQuantity(10);

        List<Inventory> inventoryList = inventoryClient.getAllInventory();
        inventoryList.add(inventory);

        doReturn(inventory).when(inventoryClient).createInventory(inventory1);
        doReturn(inventory).when(inventoryClient).getInventory(1);
        doReturn(inventory).when(inventoryClient).getInventoryByProduct(5);
        doReturn(inventoryList).when(inventoryClient).getAllInventory();
    }

    @Test
    public void saveFindInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(5);
        inventory.setQuantity(10);

        inventory = adminService.createInventory(inventory);
        Inventory inventory1 = adminService.getInventory(inventory.getInventoryId());
        assertEquals(inventory1, inventory);
    }




    private void invoiceMockSetUp(){
        invoiceClient = mock(InvoiceClient.class);

        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setId(1);
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2019,11,12));

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(5);
        invoice1.setPurchaseDate(LocalDate.of(2019,11,12));

        // USED FOR UPDATE
        InvoiceViewModel invoice2 = new InvoiceViewModel();
        invoice2.setId(4);
        invoice2.setCustomerId(8);
        invoice2.setPurchaseDate(LocalDate.of(2019,11,12));

        List<InvoiceViewModel> invoices = new ArrayList<>();
        invoices.add(invoice);

        doReturn(invoice).when(invoiceClient).createInvoice(invoice1);
        doReturn(invoice).when(invoiceClient).getInvoice(1);
        doReturn(invoices).when(invoiceClient).getAllInvoicesByCustomer(5);
        doReturn(invoice2).when(invoiceClient).getInvoice(4);
        doReturn(invoices).when(invoiceClient).getAllInvoices();
//        doNothing().when(invoiceClient).updateInvoice(invoice);

        //need to convert an ivm to invoice for update invoice via feign 

        //====================================  InvoiceItem =========================================================

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(2);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setUnitPrice(new BigDecimal("20.00"));

        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceId(1);
        invoiceItem1.setInventoryId(1);
        invoiceItem1.setQuantity(10);
        invoiceItem1.setUnitPrice(new BigDecimal("20.00"));

        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoiceItemId(5);
        invoiceItem2.setInvoiceId(3);
        invoiceItem2.setInventoryId(1);
        invoiceItem2.setQuantity(10);
        invoiceItem2.setUnitPrice(new BigDecimal("20.00"));

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        doReturn(invoiceItem).when(invoiceClient).createInvoiceItem(invoiceItem1);
        doReturn(invoiceItem).when(invoiceClient).getInvoiceItem(2);
        doReturn(invoiceItem2).when(invoiceClient).getInvoiceItem(5);
//        doReturn(invoiceItems).when(invoiceClient).getAllInvoiceItemsByInvoice(1);
        //^^Was not in the invoice controller so I did not add it to the feign client

        doReturn(invoiceItems).when(invoiceClient).getAllInvoiceItems();
        doNothing().when(invoiceClient).updateInvoiceItem(invoiceItem);

    }

    private void levelUpMockSetUp(){
        levelUpClient = mock(LevelUpClient.class);

        LevelUp levelUp = new LevelUp();
        levelUp.setLevelUpId(1);
        levelUp.setCustomerId(5);
        levelUp.setPoints(100);
        levelUp.setMemberDate(LocalDate.of(2019,11,10));

        LevelUp levelUp1 = new LevelUp();
        levelUp1.setCustomerId(5);
        levelUp1.setPoints(100);
        levelUp1.setMemberDate(LocalDate.of(2019,11,10));

        List<LevelUp> levelUps = new ArrayList<>();
        levelUps.add(levelUp);

        doReturn(levelUp).when(levelUpClient).createLevelUp(levelUp1);
        doReturn(levelUp).when(levelUpClient).getLevelUp(1);
        doReturn(levelUp).when(levelUpClient).getLevelUpByCustomer(5);
        doReturn(levelUps).when(levelUpClient).getLevelUps();

    }

    private void productMockSetUp(){
        productClient = mock(ProductClient.class);

        Product productA = new Product();
        productA.setProductId(1);
        productA.setName("Xbox Scorpion");
        productA.setDescription("Microsoft's latest console");
        productA.setListPrice(new BigDecimal("599.99"));
        productA.setUnitCost(new BigDecimal("550.99"));

        Product productB = new Product();
        productB.setName("Xbox Scorpion");
        productB.setDescription("Microsoft's latest console");
        productB.setListPrice(new BigDecimal("599.99"));
        productB.setUnitCost(new BigDecimal("550.99"));

        doReturn(productA).when(productClient).createProduct(productB);
        doReturn(productA).when(productClient).getProduct(productA.getProductId());
        //====================================================

        Product productC = new Product();
        productC.setProductId(2);
        productC.setName("Call of Duty: Modern Warfare");
        productC.setDescription("Activision's newest addition, coming oct 22");
        productC.setListPrice(new BigDecimal("59.99"));
        productC.setUnitCost(new BigDecimal("50.99"));

        Product productD = new Product();
        productD.setName("Call of Duty: Modern Warfare");
        productD.setDescription("Activision's newest addition, coming oct 22");
        productD.setListPrice(new BigDecimal("59.99"));
        productD.setUnitCost(new BigDecimal("50.99"));

        doReturn(productC).when(productClient).createProduct(productD);
        doReturn(productC).when(productClient).getProduct(productC.getProductId());
        //===================================================
        //Product List, size 2

        List<Product> productList = new ArrayList<>();
        productList.add(productA);
        productList.add(productC);

        doReturn(productList).when(productClient).getAllProducts();


        //===================================================
        //UPDATE

        Product productNoId = new Product();
        productNoId.setName("Razer Blade Laptop 14' ");
        productNoId.setDescription("Razer's 14 inch gaming laptop");
        productNoId.setListPrice(new BigDecimal("2499.99"));
        productNoId.setUnitCost(new BigDecimal("2399.99"));

        Product productNotUpdatedId = new Product();
        productNotUpdatedId.setProductId(22);
        productNotUpdatedId.setName("Razer Blade Laptop 14' ");
        productNotUpdatedId.setDescription("Razer's 14 inch gaming laptop");
        productNotUpdatedId.setListPrice(new BigDecimal("2499.99"));
        productNotUpdatedId.setUnitCost(new BigDecimal("2399.99"));

        Product productUpdated = new Product();
        productUpdated.setProductId(22);
        productUpdated.setName("Razer Blade Laptop 14' ");
        productUpdated.setDescription("Razer's 14 inch gaming laptop");
        productUpdated.setListPrice(new BigDecimal("2299.99")); //Sale of $200
        productUpdated.setUnitCost(new BigDecimal("2099.99"));

        doReturn(productNotUpdatedId).when(productClient).createProduct(productNoId);
        doNothing().when(productClient).updateProduct(productUpdated);
        doReturn(productUpdated).when(productClient).getProduct(productUpdated.getProductId());

        //===================================================
        //DELETE

        Product productDelete = new Product();
        productDelete.setProductId(99);
        productDelete.setName("Macbook Pro 15' ");
        productDelete.setDescription("15 inch macbook pro");
        productDelete.setListPrice(new BigDecimal("2100.99"));
        productDelete.setUnitCost(new BigDecimal("1999.99"));

        doNothing().when(productClient).deleteProduct(productDelete.getProductId());
        doReturn(null).when(productClient).getProduct(productDelete.getProductId());
    }
















}
