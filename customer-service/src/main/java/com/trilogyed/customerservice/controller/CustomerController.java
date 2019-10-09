package com.trilogyed.customerservice.controller;

import com.trilogyed.customerservice.exception.NotFoundException;
import com.trilogyed.customerservice.model.Customer;
import com.trilogyed.customerservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RefreshScope
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    ServiceLayer service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody @Valid Customer customer){return service.createCustomer(customer);}

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id){
        Customer customerFromService = service.getCustomer(id);
        if(customerFromService==null)
            throw new NotFoundException("No customer exists in the DB with given id: "+id);
        return customerFromService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers(){return service.getAllCustomers();}

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateCustomer(@RequestBody @Valid Customer customer){
        service.updateCustomer(customer);
        return "Customer Updated.";
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCustomer(@PathVariable int id){
        service.deleteCustomer(id);
        return "Customer deleted";
    }




}
