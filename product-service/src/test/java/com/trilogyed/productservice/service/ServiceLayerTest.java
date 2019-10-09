package com.trilogyed.productservice.service;

import com.trilogyed.productservice.dao.ProductDao;
import org.junit.Before;

public class ServiceLayerTest {
    ProductDao dao;

    ServiceLayer service;

    @Before
    public void setUp() throws Exception{
        setUpProductDaoMock();

        service = new ServiceLayer(dao);

    }


    private void setUpProductDaoMock(){


    }


}
