package com.trilogyed.retailapi.util.feign;

import com.trilogyed.retailapi.model.Invoice;
import com.trilogyed.retailapi.model.InvoiceItem;
import com.trilogyed.retailapi.viewmodel.InvoiceViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-service")
@RequestMapping(value = "/invoices")
public interface InvoiceClient {
    @PostMapping
    InvoiceViewModel createInvoice(@RequestBody Invoice invoice);

    @GetMapping(value = "/{id}")
    InvoiceViewModel getInvoiceById(@PathVariable int id);

    @GetMapping("/customer/{customerId}")
    List<InvoiceViewModel> getAllInvoicesByCustomer(@PathVariable int customerId);

    @PostMapping(value = "/invoice-items")
    InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

}
