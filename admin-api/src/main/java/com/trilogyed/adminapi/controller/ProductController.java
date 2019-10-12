package com.trilogyed.adminapi.controller;

import com.trilogyed.adminapi.exception.NullObjectReturnException;
import com.trilogyed.adminapi.model.Customer;
import com.trilogyed.adminapi.model.Product;
import com.trilogyed.adminapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(name = "/admin/products")
public class ProductController {

    @Autowired
    AdminService adminService;

    public ProductController(AdminService adminService){this.adminService = adminService;}

    @PostMapping
    public Product createProduct(@RequestBody @Valid Product product){
        return adminService.createProduct(product);
    }

    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable int id){
        if(adminService.getProduct(id)== null)
            throw new NullObjectReturnException("There exists no Product with the given id in the Db; ID: "+ id);
        return adminService.getProduct(id);
    }

    @GetMapping
    public List<Product> getAllProducts(){return adminService.getAllProducts();}

    @PutMapping
    public void updateProduct(@RequestBody @Valid Product product){
        adminService.updateProduct(product);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable int id){adminService.deleteProduct(id);}
}
