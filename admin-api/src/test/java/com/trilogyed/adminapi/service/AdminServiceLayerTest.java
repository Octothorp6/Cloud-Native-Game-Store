package com.trilogyed.adminapi.service;

import com.trilogyed.adminapi.model.Customer;
import com.trilogyed.adminapi.model.Inventory;
import com.trilogyed.adminapi.model.Invoice;
import com.trilogyed.adminapi.model.InvoiceItem;
import com.trilogyed.adminapi.service.AdminService;
import com.trilogyed.adminapi.util.feign.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        /*
    public Customer createCustomer(Customer customer)
    public Customer getCustomer(int customerId)
     public List<Customer> getAllCustomers()
     public void updateCustomer(Customer customer)
     */

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


    private void invoiceMockSetUp(){
        invoiceClient = mock(InvoiceClient.class);

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2019,11,12));

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(5);
        invoice1.setPurchaseDate(LocalDate.of(2019,11,12));

        // USED FOR UPDATE
        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceId(4);
        invoice2.setCustomerId(8);
        invoice2.setPurchaseDate(LocalDate.of(2019,11,12));

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);

        doReturn(invoice).when(invoiceClient).createInvoice(invoice1);
        doReturn(invoice).when(invoiceClient).getInvoice(1);
        doReturn(invoices).when(invoiceClient).getAllInvoicesByCustomer(5);
        doReturn(invoice2).when(invoiceClient).getInvoice(4);
        doReturn(invoices).when(invoiceClient).getAllInvoices();
        doNothing().when(invoiceClient).updateInvoice(invoice);

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

    }

    private void productMockSetUp(){

    }
















}
