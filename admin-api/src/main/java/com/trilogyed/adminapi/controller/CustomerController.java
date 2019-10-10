package com.trilogyed.adminapi.controller;

import com.trilogyed.adminapi.model.Customer;
import com.trilogyed.adminapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(name = "/admin/customer")
public class CustomerController {

    @Autowired
    AdminService adminService;

    public CustomerController(AdminService adminService){this.adminService = adminService;}


    //CRUD w/ Authorization for Customer
    @PostMapping
    public Customer createCustomer(Principal principal, @RequestBody @Valid Customer customer){
        return adminService.createCustomer(customer);
    }

    @GetMapping(value = "/{id}")
    public Customer getCustomer( @PathVariable int id){
        return adminService.getCustomer(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers(){return adminService.getAllCustomers(); }

    @PutMapping
    public void updateCustomer(Principal principal, @RequestBody @Valid Customer customer){
        adminService.updateCustomer(customer);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCustomer(Principal principal, @PathVariable int id){adminService.deleteCustomer(id);}


}
