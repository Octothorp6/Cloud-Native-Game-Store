package com.trilogyed.customerservice.controller;

import com.trilogyed.customerservice.exception.NotFoundException;
import com.trilogyed.customerservice.model.Customer;
import com.trilogyed.customerservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/customers")
@CacheConfig(cacheNames = {"customers"})
public class CustomerController {

    @Autowired
    ServiceLayer service;

    @CachePut(key = "#result.getCustomerId()")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody @Valid Customer customer){return service.createCustomer(customer);}


    @Cacheable
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id){
        Customer customerFromService = service.getCustomer(id);
        return customerFromService;
    }

    @GetMapping //do we add value = "/customers" ??
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers(){return service.getAllCustomers();}


    @CacheEvict(key = "#result.getCustomerId()")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody @Valid Customer customer){
        service.updateCustomer(customer);
//        return "Customer Updated.";
    }

    @CacheEvict
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable int id){
        service.deleteCustomer(id);
//        return "Customer deleted";
    }

}
