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
    Customer createCustomer(@RequestBody @Valid Customer customer);

    @GetMapping(value = "/{id}")
    Customer getCustomer(@PathVariable int id);

    @GetMapping
    List<Customer> getAllCustomers();

    @PutMapping
    void updateCustomer(@RequestBody @Valid Customer customer);

    @DeleteMapping(value = "/{id}")
    void deleteCustomer(@PathVariable int id);

}

