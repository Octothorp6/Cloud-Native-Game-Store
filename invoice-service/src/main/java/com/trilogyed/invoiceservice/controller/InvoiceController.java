package com.trilogyed.invoiceservice.controller;

import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import com.trilogyed.invoiceservice.service.ServiceLayer;
import com.trilogyed.invoiceservice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    // INVOICE ROUTES

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody Invoice invoice) {
        return serviceLayer.saveInvoice(invoice);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {
        return serviceLayer.findAllInvoices();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody Invoice invoice) {
        serviceLayer.updateInvoice(invoice);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id) {
        serviceLayer.removeInvoice(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return serviceLayer.findInvoice(id);
    }

    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoicesByCustomer(@PathVariable int customerId) {
        return serviceLayer.findInvoicesByCustomer(customerId);
    }

    //========================================================================================================
    //========================================================================================================
    // INVOICE ITEM ROUTES

    @PostMapping(value = "/invoice-items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public InvoiceItem createInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        return serviceLayer.saveInvoiceItem(invoiceItem);
    }

    @GetMapping(value = "/invoice-items")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItem> getAllInvoiceItems() {
        return serviceLayer.findAllInvoiceItems();
    }

    @GetMapping(value = "/invoice-items/{invoiceId}")
    public List<InvoiceItem> getInvoiceItemsById(@PathVariable int invoiceId) {
        return serviceLayer.findInvoiceItemsByInvoice(invoiceId);
    }

    @PutMapping(value = "/invoice-items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        serviceLayer.updateInvoiceItem(invoiceItem);
    }

    @DeleteMapping(value = "/invoice-items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceItem(@PathVariable int id) {
        serviceLayer.removeInvoiceItem(id);
    }

}
