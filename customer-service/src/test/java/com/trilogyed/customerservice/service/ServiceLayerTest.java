package com.trilogyed.customerservice.service;

import com.trilogyed.customerservice.dao.CustomerDao;
import com.trilogyed.customerservice.dao.CustomerDaoJdbcTemplateImpl;
import com.trilogyed.customerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    CustomerDao dao;

    ServiceLayer service;

    @Before
    public void setUp() throws Exception{
        setUpCustomerDaoMock();

        service = new ServiceLayer(dao);

    }
    /*
      customer_id int(11) not null auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    zip varchar(10) not null,
    email varchar(75) not null,
    phone varchar(20) not null
     */

    private void setUpCustomerDaoMock(){
        dao = mock(CustomerDaoJdbcTemplateImpl.class);

        //Add one obj
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

        doReturn(custA).when(dao).createCustomer(customerB);
        doReturn(custA).when(dao).getCustomer(custA.getCustomerId());
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

        doReturn(custC).when(dao).createCustomer(customerD);
        doReturn(custC).when(dao).getCustomer(custC.getCustomerId());

        //============================================================
        //List of Customers size 2
        List<Customer> customerList = new ArrayList<>();
        customerList.add(custA);
        customerList.add(custC);

        doReturn(customerList).when(dao).getAllCustomers();

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

        doReturn(customerNotUpdatedId).when(dao).createCustomer(customerNoId);
        doNothing().when(dao).updateCustomer(customerUpdatedId);
        doReturn(customerUpdatedId).when(dao).getCustomer(customerUpdatedId.getCustomerId());

        //============================================================
        //Delete

        Customer customerDelete = new Customer();
        customerDelete.setCustomerId(99);
        customerDelete.setFirstName("The");
        customerDelete.setLastName("Oracle");
        customerDelete.setStreet("The Park");
        customerDelete.setCity("The Simulation");
        customerDelete.setZip("11001");
        customerDelete.setEmail("theOracle@thematrix.gov");
        customerDelete.setPhone("404404404");

        doNothing().when(dao).deleteCustomer(customerDelete.getCustomerId());
        doReturn(null).when(dao).getCustomer(customerDelete.getCustomerId());
    }

    @Test
    public void createGetGetAllCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Neo");
        customer.setLastName("The One");
        customer.setStreet("230 wabash");
        customer.setCity("The Matrix");
        customer.setZip("11111");
        customer.setEmail("neo@matrix.com");
        customer.setPhone("091144564");

        customer = service.createCustomer(customer);

        Customer customerFromService = service.getCustomer(customer.getCustomerId());
        assertEquals(customerFromService,customer);
        //is this right? expected,actual right?

        Customer customer2 = new Customer();
        customer2.setFirstName("Trinity");
        customer2.setLastName("T");
        customer2.setStreet("230 wabash");
        customer2.setCity("The Matrix");
        customer2.setZip("11111");
        customer2.setEmail("trinity@matrix.com");
        customer2.setPhone("091144564");

        customer2 = service.createCustomer(customer2);

        List<Customer> customerList = service.getAllCustomers();
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

        customerToUpdate = service.createCustomer(customerToUpdate);

        customerToUpdate.setStreet("240 wabash");
        customerToUpdate.setEmail("morpheus@zion.org");
        customerToUpdate.setPhone("091144569");

        service.updateCustomer(customerToUpdate);

        Customer customerUpdated = service.getCustomer(customerToUpdate.getCustomerId());

        assertEquals(customerUpdated,customerToUpdate);
    }

    @Test
    public void deleteCustomer(){
        Customer customerDelete = new Customer();
        customerDelete.setCustomerId(99);
        customerDelete.setFirstName("The");
        customerDelete.setLastName("Oracle");
        customerDelete.setStreet("The Park");
        customerDelete.setCity("The Simulation");
        customerDelete.setZip("11001");
        customerDelete.setEmail("theOracle@thematrix.gov");
        customerDelete.setPhone("404404404");

        service.deleteCustomer(customerDelete.getCustomerId());

        Customer customerRemoved = service.getCustomer(customerDelete.getCustomerId());

        assertNull(customerRemoved);
    }
}
