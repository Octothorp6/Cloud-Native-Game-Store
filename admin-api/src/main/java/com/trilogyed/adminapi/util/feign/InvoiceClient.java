package com.trilogyed.adminapi.util.feign;

import com.sun.corba.se.pept.transport.InboundConnectionCache;
import com.trilogyed.adminapi.model.Invoice;
import com.trilogyed.adminapi.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-service")
@RequestMapping("/invoices")
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

    //==============================    InvocieItem Crud ================================

    @PostMapping(value = "/invoice-items")
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @GetMapping(value = "/invoice-items/{id}")
    public InvoiceItem getInvoiceItem(@PathVariable int id);

    @GetMapping(value = "/invoice-items")
    public List<InvoiceItem> getAllInvoiceItems();

    @PutMapping(value = "/invoice-items")
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem);

    @DeleteMapping(value = "/invoice-items/{id}")
    public void deleteInvoiceItem(@PathVariable int id);

}
