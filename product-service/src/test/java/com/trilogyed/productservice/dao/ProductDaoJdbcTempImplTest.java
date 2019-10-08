package com.trilogyed.productservice.dao;

import com.trilogyed.productservice.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductDaoJdbcTempImplTest {

    @Autowired
    protected ProductDao productDao;

    @Before
    public void setUp() throws Exception{}




    @Test
    public void createProduct() {
    }

    @Test
    public void getProduct() {
    }

    @Test
    public void getAllProducts() {
    }

    @Test
    public void updateProduct() {
    }

    @Test
    public void deleteProduct() {
    }
}