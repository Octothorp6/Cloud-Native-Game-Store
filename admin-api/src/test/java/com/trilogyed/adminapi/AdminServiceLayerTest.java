package com.trilogyed.adminapi;

import com.trilogyed.adminapi.model.Customer;
import com.trilogyed.adminapi.service.AdminService;
import com.trilogyed.adminapi.util.feign.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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


    }

    private void invoiceMockSetUp(){

    }

    private void levelUpMockSetUp(){

    }

    private void productMockSetUp(){

    }
















}
