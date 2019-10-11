package com.trilogyed.invoiceservice.controller;

import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import com.trilogyed.invoiceservice.service.ServiceLayer;
import com.trilogyed.invoiceservice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    // INVOICE ROUTES

    @PostMapping
    public InvoiceViewModel createInvoice(Invoice invoice) {
        return serviceLayer.saveInvoice(invoice);
    }

    @GetMapping
    public List<InvoiceViewModel> getAllInvoices() {
        return serviceLayer.findAllInvoices();
    }

    @PutMapping
    public void updateInvoice(Invoice invoice) {
        serviceLayer.updateInvoice(invoice);
    }

    @GetMapping("/{id}")
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return serviceLayer.findInvoice(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<InvoiceViewModel> getAllInvoicesByCustomer(@PathVariable int customerId) {
        return serviceLayer.findInvoicesByCustomer(customerId);
    }

    //========================================================================================================
    //========================================================================================================
    // INVOICE ITEM ROUTES

    @PostMapping(value = "/invoice-items")
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        return serviceLayer.saveInvoiceItem(invoiceItem);
    }

    @GetMapping(value = "/invoice-items")
    public List<InvoiceItem> getAllInvoiceItems() {
        return serviceLayer.findAllInvoiceItems();
    }

    @PutMapping(value = "/invoice-items")
    public void updateInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        serviceLayer.updateInvoiceItem(invoiceItem);
    }

    @DeleteMapping(value = "/invoice-items/{id}")
    public void deleteInvoiceItem(@PathVariable int id) {
        serviceLayer.removeInvoiceItem(id);
    }

}
