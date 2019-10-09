package com.trilogyed.customerservice.dao;

import com.trilogyed.customerservice.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoJdbcTemplateImplTest {

    @Autowired
    protected CustomerDao dao;

    @Before
    public void setUp()throws Exception{
        List<Customer> deleteList = dao.getAllCustomers();
        deleteList.stream().forEach(customer -> dao.deleteCustomer(customer.getCustomerId()));
    }

    @After
    public void tearDown()throws Exception{
        List<Customer> deleteList = dao.getAllCustomers();
        deleteList.stream().forEach(customer -> dao.deleteCustomer(customer.getCustomerId()));
    }

    @Test
    public void createGetGetAllDeleteCustomer() {
        //Create
        Customer customer = new Customer();
        customer.setFirstName("Neo");
        customer.setLastName("Neo");
        customer.setStreet("230 wabash");
        customer.setCity("The Matrix");
        customer.setZip("11111");
        customer.setEmail("neo@matrix.com");
        customer.setPhone("091144564");

        customer = dao.createCustomer(customer);

        //Get
        Customer cust2 = dao.getCustomer(customer.getCustomerId());
        assertEquals(cust2, customer);

        //GetAll
        List<Customer> cList = dao.getAllCustomers();
        assertEquals(cList.size(), 1);

        //Delete
        dao.deleteCustomer(customer.getCustomerId());
        cust2 = dao.getCustomer(customer.getCustomerId());

        assertNull(cust2);
    }

    @Test
    public void updateCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Neo");
        customer.setLastName("Neo");
        customer.setStreet("230 wabash");
        customer.setCity("The Matrix");
        customer.setZip("11111");
        customer.setEmail("neo@matrix.com");
        customer.setPhone("091144564");

        customer = dao.createCustomer(customer);

        customer.setFirstName("Morpheus");
        customer.setLastName("M");
        customer.setEmail("morph@zion.org");
        dao.updateCustomer(customer);

        Customer custUpdate = dao.getCustomer(customer.getCustomerId());
        assertEquals(customer,custUpdate);
    }


}
