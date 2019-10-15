package com.trilogyed.productservice.dao;

import com.trilogyed.productservice.model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductDaoJdbcTempImplTest {

    @Autowired
    protected ProductDao dao;

    @Before
    public void setUp() throws Exception {
        List<Product> deleteList = dao.getAllProducts();
        deleteList.stream().forEach(product -> dao.deleteProduct(product.getProductId()));
    }

    @After
    public void tearDown() throws Exception {
        List<Product> deleteList = dao.getAllProducts();
        deleteList.stream().forEach(product -> dao.deleteProduct(product.getProductId()));
    }


    @Test
    public void createGetGetAllDeleteProduct() {
        //Create
        Product product = new Product();
        product.setName("Xbox 1");
        product.setDescription("Microsoft's latest console");
        product.setListPrice(new BigDecimal("299.99"));
        product.setUnitCost(new BigDecimal("250.99"));

        product = dao.createProduct(product);

        //Get
        Product product2 = dao.getProduct(product.getProductId());
        assertEquals(product2,product);

        //GetAll
        List<Product> pList = dao.getAllProducts();
        assertEquals(pList.size(),1);

        //Delete
        dao.deleteProduct(product.getProductId());
        product2 = dao.getProduct(product.getProductId());
        assertNull(product2);

    }


    @Test
    public void getProductByName() {
        Product sony = new Product();
        sony.setName("PS5");
        sony.setDescription("Sony's latest console");
        sony.setListPrice(new BigDecimal("499.99"));
        sony.setUnitCost(new BigDecimal("470.99"));
        sony = dao.createProduct(sony);

        Product product = dao.getProductByName("PS5");
        assertEquals(product, sony);
    }

    @Test
    public void updateProduct() {

        Product sony = new Product();
        sony.setName("PS5");
        sony.setDescription("Sony's latest console");
        sony.setListPrice(new BigDecimal("499.99"));
        sony.setUnitCost(new BigDecimal("470.99"));

        sony = dao.createProduct(sony);

        sony.setListPrice(new BigDecimal("469.99"));
        sony.setUnitCost(new BigDecimal("459.99"));
        dao.updateProduct(sony);

        Product productUpdate = dao.getProduct(sony.getProductId());
        assertEquals(sony,productUpdate);
    }

}