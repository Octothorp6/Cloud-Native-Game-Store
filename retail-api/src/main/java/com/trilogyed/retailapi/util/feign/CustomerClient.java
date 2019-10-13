package com.trilogyed.retailapi.util.feign;

import com.trilogyed.retailapi.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "customer-service")
@RequestMapping(value = "/customers")
public interface CustomerClient {

    Customer createCustomer(@RequestBody @Valid Customer customer);

    @GetMapping(value = "/{id}")
    Customer getCustomer(@PathVariable int id);

    @GetMapping //do we add value = "/customers" ??
    List<Customer> getAllCustomers();
}
