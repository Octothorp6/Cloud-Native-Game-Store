package com.trilogyed.productservice.dao;

import com.trilogyed.productservice.model.Product;

import java.util.List;

public interface ProductDao {

    Product createProduct(Product product);
    Product getProduct(int productId);
    Product getProductByName(String productName);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int productId);

}
