package com.trilogyed.invoiceservice.controller;

import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import com.trilogyed.invoiceservice.service.ServiceLayer;
import com.trilogyed.invoiceservice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    // INVOICE ROUTES

    @PostMapping
    public Invoice createInvoice(Invoice invoice) {
        return serviceLayer.saveInvoice(invoice);
    }

    @GetMapping("/{id}")
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return serviceLayer.getInvoice(id);
    }

    //========================================================================================================
    //========================================================================================================
    // INVOICE ITEM ROUTES

    @PostMapping("/invoiceItems")
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        return serviceLayer.saveInvoiceItem(invoiceItem);
    }


}
