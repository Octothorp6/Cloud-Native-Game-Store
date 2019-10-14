package com.trilogyed.adminapi.util.feign;


import com.trilogyed.adminapi.model.Customer;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@FeignClient(name = "customer-service")
@RequestMapping("/customers")
public interface CustomerClient {

    //Admin API responsible for full CRUD

    @PostMapping
    public Customer createCustomer(@RequestBody @Valid Customer customer);

    @GetMapping(value = "/{id}")
    public Customer getCustomer(@PathVariable int id);

    @GetMapping
    public List<Customer> getAllCustomers();

    @PutMapping
    public void updateCustomer(@RequestBody @Valid Customer customer);

    @DeleteMapping(value = "/{id}")
    public void deleteCustomer(@PathVariable int id);

}

