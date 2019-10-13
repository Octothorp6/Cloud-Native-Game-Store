package com.trilogyed.retailapi.util.feign;

import com.trilogyed.retailapi.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "product-service")
@RequestMapping(value = "/products")
public interface ProductClient {
    @GetMapping(value = "/{id}")
    Product getProduct(@PathVariable int id);

    @GetMapping
    List<Product> getAllProducts();

    @GetMapping(value = "/name/{name}")
    Product getProductByName(String name);

}
