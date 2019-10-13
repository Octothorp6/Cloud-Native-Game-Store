package com.trilogyed.retailapi.util.feign;

import com.trilogyed.retailapi.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "customer-service")
@RequestMapping(value = "/customers")
public interface CustomerClient {
    @PostMapping
    Customer createCustomer(@RequestBody @Valid Customer customer);

    @GetMapping(value = "/{id}")
    Customer getCustomer(@PathVariable int id);

    @GetMapping //do we add value = "/customers" ??
    List<Customer> getAllCustomers();
}
