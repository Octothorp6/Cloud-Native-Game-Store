package com.trilogyed.productservice.dao;

import com.trilogyed.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            "INSERT INTO product (product_name, product_description, list_price, unit_cost) values(?,?,?,?)";

    private static final String SELECT_PRODUCT_SQL =
            "SELECT * FROM product WHERE product_id = ?";

    private static final String SELECT_ALL_PRODUCTS_SQL =
            "SELECT * FROM product";

    private static final String UPDATE_PRODUCT_SQL =
            "UPDATE product set product_name = ?, product_description = ?, list_price = ?, unit_cost = ? WHERE product_id = ?";

    private static final String DELETE_PRODUCT_SQL =
            "DELETE FROM product WHERE product_id = ?";

    @Autowired
    public ProductDaoJdbcTempImpl(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    @Transactional
    public Product createProduct(Product product) {
        jdbcTemplate.update(
                INSERT_PRODUCT_SQL,
                product.getName(),
                product.getDescription(),
                product.getListPrice(),
                product.getUnitCost());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",Integer.class);
        product.setProductId(id);

        return product;
    }

    @Override
    public Product getProduct(int productId) {
        try{
            return jdbcTemplate.queryForObject(SELECT_PRODUCT_SQL, this::mapRowToProduct,productId);
        }catch (EmptyResultDataAccessException e){

            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SELECT_ALL_PRODUCTS_SQL,this::mapRowToProduct);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT_SQL,
                product.getName(),
                product.getDescription(),
                product.getListPrice(),
                product.getUnitCost(),
                product.getProductId()
        );
    }

    @Override
    public void deleteProduct(int productId) { jdbcTemplate.update(DELETE_PRODUCT_SQL,productId); }

    /*
product_id int(11) not null auto_increment primary key,
    product_name varchar(50) not null,
    product_description varchar(255) not null,
    list_price decimal(7,2) not null,
    unit_cost decimal(7,2) not null

 */

    private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException{

        Product product = new Product();

        product.setProductId(rs.getInt("product_id"));
        product.setName(rs.getString("product_name"));
        product.setDescription(rs.getString("product_description"));
        product.setListPrice(rs.getBigDecimal("list_price"));
        product.setUnitCost(rs.getBigDecimal("unit_cost"));
        
        return product;
    }

}
