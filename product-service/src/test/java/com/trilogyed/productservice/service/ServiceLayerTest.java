package com.trilogyed.productservice.service;

import com.trilogyed.productservice.dao.ProductDao;
import com.trilogyed.productservice.dao.ProductDaoJdbcTempImpl;
import com.trilogyed.productservice.model.Product;
import org.junit.Before;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    ProductDao dao;

    ServiceLayer service;

    @Before
    public void setUp() throws Exception{
        setUpProductDaoMock();

        service = new ServiceLayer(dao);

    }


    private void setUpProductDaoMock(){
        /*
        product_id int(11) not null auto_increment primary key,
    product_name varchar(50) not null,
    product_description varchar(255) not null,
    list_price decimal(7,2) not null,
    unit_cost decimal(7,2) not null

         */
        dao = mock(ProductDaoJdbcTempImpl.class);

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

        doReturn(productA).when(dao).createProduct(productB);
        doReturn(productA).when(dao).getProduct(1);
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

        doReturn(productC).when(dao).createProduct(productD);
        doReturn(productC).when(dao).getProduct(2);
        //===================================================
        //Product List, size 2

        List<Product> productList = new ArrayList<>();
        productList.add(productA);
        productList.add(productC);

        doReturn(productList).when(dao).getAllProducts();


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
        productUpdated.setName("Razer Blade Laptop 14' ");
        productUpdated.setDescription("Razer's 14 inch gaming laptop");
        productUpdated.setListPrice(new BigDecimal("2299.99")); //Sale of $200
        productUpdated.setUnitCost(new BigDecimal("2099.99"));






    }


}
