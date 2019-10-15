package com.trilogyed.adminapi.controller;

import com.trilogyed.adminapi.exception.NotFoundException;
import com.trilogyed.adminapi.model.Customer;
import com.trilogyed.adminapi.service.AdminService;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RefreshScope
public class CustomerController {

    @Autowired
    AdminService adminService;

    public CustomerController(AdminService adminService){this.adminService = adminService;}


    //CRUD w/ Authorization for Customer
//    @PostMapping
    @RequestMapping(value = "/admin/customers",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer createCustomer( @RequestBody @Valid Customer customer){
        return adminService.createCustomer(customer);
    }

//    @GetMapping(value = "/{id}")
    @RequestMapping(value = "/admin/customers/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Customer getCustomer( @PathVariable int id){
        Customer customerFromService = adminService.getCustomer(id);
        if(customerFromService==null)
            throw new NotFoundException("No customer exists in the DB with given id: "+id);
        return customerFromService;
    }

//    @GetMapping
    @RequestMapping(value = "/admin/customers/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> getAllCustomers(){return adminService.getAllCustomers(); }

//    @PutMapping
    @RequestMapping(value = "/admin/customers", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer( @RequestBody @Valid Customer customer){
        adminService.updateCustomer(customer);
    }



//    @DeleteMapping(value = "/{id}")
//    public void deleteCustomer( Principal principal,@PathVariable int id){adminService.deleteCustomer(id);}

//Below is the controller with SECURITYU

    //CRUD w/ Authorization for Customer
//    @PostMapping
//    @RequestMapping(value = "/admin/customers",method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public Customer createCustomer(Principal principal, @RequestBody @Valid Customer customer){
//        return adminService.createCustomer(customer);
//    }
//
//    //    @GetMapping(value = "/{id}")
//    @RequestMapping(value = "/admin/customers/{id}",method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public Customer getCustomer( @PathVariable int id){
//        Customer customerFromService = adminService.getCustomer(id);
//        if(customerFromService==null)
//            throw new NotFoundException("No customer exists in the DB with given id: "+id);
//        return customerFromService;
//    }
//
//    //    @GetMapping
//    @RequestMapping(value = "/admin/customers/all", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<Customer> getAllCustomers(){return adminService.getAllCustomers(); }
//
//    //    @PutMapping
//    @RequestMapping(value = "/admin/customer", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateCustomer(Principal principal, @RequestBody @Valid Customer customer){
//        adminService.updateCustomer(customer);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public void deleteCustomer( Principal principal,@PathVariable int id){adminService.deleteCustomer(id);}



}
