package com.trilogyed.adminapi.controller;

import com.trilogyed.adminapi.exception.NullObjectReturnException;
import com.trilogyed.adminapi.model.Customer;
import com.trilogyed.adminapi.model.Product;
import com.trilogyed.adminapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
//@RequestMapping(name = "/admin/products")
public class ProductController {

    @Autowired
    AdminService adminService;

    public ProductController(AdminService adminService){this.adminService = adminService;}

    @RequestMapping(value = "/admin/products",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Valid Product product){
        return adminService.createProduct(product);
    }

    @RequestMapping(value = "/admin/products/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Product getProduct(@PathVariable int id){
        if(adminService.getProduct(id)== null)
            throw new NullObjectReturnException("There exists no Product with the given id in the Db; ID: "+ id);
        return adminService.getProduct(id);
    }

    @RequestMapping(value = "/admin/products/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Product> getAllProducts(){return adminService.getAllProducts();}

    @RequestMapping(value = "/admin/product", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody @Valid Product product){
        adminService.updateProduct(product);
    }



//    @DeleteMapping(value = "/{id}")
//    public void deleteProduct(@PathVariable int id){adminService.deleteProduct(id);}
}
