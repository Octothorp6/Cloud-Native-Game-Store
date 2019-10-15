package com.trilogyed.adminapi.util.feign;

import com.trilogyed.adminapi.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "product-service")
@RequestMapping("/products")
public interface ProductClient {

    @PostMapping
    Product createProduct(@RequestBody @Valid Product product);

    @GetMapping(value = "/{id}")
    Product getProduct(@PathVariable int id);

    @GetMapping
    List<Product> getAllProducts();

    @PutMapping
    void updateProduct(@RequestBody @Valid Product product);

    @DeleteMapping(value = "/{id}")
    void deleteProduct(@PathVariable int id);
}
