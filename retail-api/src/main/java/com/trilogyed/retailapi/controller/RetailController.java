package com.trilogyed.retailapi.controller;

import com.trilogyed.retailapi.exception.NotFoundException;
import com.trilogyed.retailapi.model.*;
import com.trilogyed.retailapi.service.ServiceLayer;
import com.trilogyed.retailapi.util.messages.LevelUpEntry;
import com.trilogyed.retailapi.viewmodel.InvoiceViewModel;
import com.trilogyed.retailapi.viewmodel.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/retail")
public class RetailController {
    private ServiceLayer serviceLayer;

    @Autowired
    public RetailController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    // RETAIL ENDPOINTS
    @PostMapping(value = "/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderViewModel submitInvoice(@RequestBody @Valid Order order) {
        return serviceLayer.submitInvoice(order);
    }

    @GetMapping(value = "/invoices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        InvoiceViewModel invoiceViewModel = serviceLayer.findInvoiceById(id);
        if (invoiceViewModel == null) {
            throw new NotFoundException("Sorry, we do not have any invoices with that id: " + id);
        }
        return invoiceViewModel;
    }

    @GetMapping(value = "/invoices/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable int id) {
        List<InvoiceViewModel> invoices = serviceLayer.findInvoicesByCustomerId(id);
        if (invoices.size() == 0) {
            throw new NotFoundException("Sorry, we do not have any invoices with that customer id: " + id);
        }
        return null;
    }

    @GetMapping(value = "/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return serviceLayer.findAllProducts();
    }

    @GetMapping(value = "/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable int id) {
        return serviceLayer.findProduct(id);
    }

    @GetMapping(value = "/products/inventory")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductsInInventory() {
        return serviceLayer.findProductsInInventory();
    }

    @GetMapping(value = "/products/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductsByInvoiceId(@PathVariable int id) {
        List<Product> products = serviceLayer.findProductsByInvoiceId(id);

        if (products == null) {
            throw new NotFoundException("Sorry, we do not have any invoices by that id: " + id);
        }
        return products;
    }

    @PostMapping(value = "/level-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveLevelUp(@RequestBody LevelUpEntry level) {
        serviceLayer.saveLevelUp(level);
    }
}
