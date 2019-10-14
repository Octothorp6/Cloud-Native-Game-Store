package com.trilogyed.retailapi.util.feign;

import com.trilogyed.retailapi.model.Invoice;
import com.trilogyed.retailapi.viewmodel.InvoiceViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {
    @PostMapping
    InvoiceViewModel createInvoice(Invoice invoice);

    @GetMapping(value = "/{id}")
    InvoiceViewModel getInvoiceById(@PathVariable int id);

    @GetMapping("/customer/{customerId}")
    List<InvoiceViewModel> getAllInvoicesByCustomer(@PathVariable int customerId);
}
