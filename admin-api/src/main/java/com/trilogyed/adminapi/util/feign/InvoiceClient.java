package com.trilogyed.adminapi.util.feign;

import com.sun.corba.se.pept.transport.InboundConnectionCache;
import com.trilogyed.adminapi.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-service")
@RequestMapping("/invoice")
public interface InvoiceClient {

    @PostMapping
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice);

    @GetMapping(value = "/{id}")
    public Invoice getInvoice(@PathVariable int id);

    @GetMapping
    public List<Invoice> getAllInvoices();

    @PutMapping
    public void updateInvoice(@RequestBody @Valid Invoice invoice);

    @DeleteMapping(value = "/{id}")
    public void deleteInvoice(@PathVariable int id);

}
