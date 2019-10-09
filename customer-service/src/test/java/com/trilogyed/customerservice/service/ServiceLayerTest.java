package com.trilogyed.customerservice.service;

import com.trilogyed.customerservice.dao.CustomerDao;
import com.trilogyed.customerservice.dao.CustomerDaoJdbcTemplateImpl;
import com.trilogyed.customerservice.model.Customer;
import org.junit.Before;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

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



    }
}
