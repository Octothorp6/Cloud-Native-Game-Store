package com.trilogyed.productservice.controller;

import com.trilogyed.productservice.exception.NotFoundException;
import com.trilogyed.productservice.model.Product;
import com.trilogyed.productservice.service.ServiceLayer;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ServiceLayer service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Valid Product product){
        return service.createProduct(product);
    }

/*    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)

 */
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable int id){
        Product productFromService = service.getProduct(id);
        if(productFromService==null)
            throw new NotFoundException("No product exists in the DB with given id: "+id);
        return productFromService;
    }

    @GetMapping //do we add value = "/products"??
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){return service.getAllProducts();}

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateProduct(@RequestBody @Valid Product product){
        service.updateProduct(product);
        return "Product Updated.";
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
        return "Product deleted";
    }

}
