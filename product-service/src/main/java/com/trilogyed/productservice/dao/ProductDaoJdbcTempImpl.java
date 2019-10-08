package com.trilogyed.productservice.dao;

import com.trilogyed.productservice.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
product_id int(11) not null auto_increment primary key,
    product_name varchar(50) not null,
    product_description varchar(255) not null,
    list_price decimal(7,2) not null,
    unit_cost decimal(7,2) not null

 */
@Repository
public class ProductDaoJdbcTempImpl implements ProductDao {

    private JdbcTemplate jdbcTemplate;

    //PreparedStatements

    private static final String INSERT_PRODUCT_SQL =
            "INSERT INTO product (product_name, product_description, list_price, unit_price) values(?,?,?,?)";

    private static final String SELECT_PRODUCT_SQL =
            "SELECT * FROM product WHERE product_id = ?";





    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product getProduct(int productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(int productId) {

    }
}
