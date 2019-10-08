package com.trilogyed.productservice.dao;

import com.trilogyed.productservice.model.Product;

import java.util.List;

public interface ProductDao {

    Product createProduct(Product product);
    Product getProduct(int productId);
    List<Product> getAllProducts();
    void updateProduct(int productId);
    void deleteProduct(int productId);

}
