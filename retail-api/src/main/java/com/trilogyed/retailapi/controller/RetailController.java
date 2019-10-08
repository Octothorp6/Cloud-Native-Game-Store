package com.trilogyed.retailapi.controller;

import com.trilogyed.retailapi.model.Invoice;
import com.trilogyed.retailapi.model.Product;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/gamestore")
public class RetailController {
    // Retail Endpoints

    @GetMapping(value = "/invoices")
    public List<Invoice> getAllInvoices() {
        return null;
    }

    @PostMapping(value = "/invoices")
    public Invoice submitInvoice(@RequestBody @Valid Invoice invoice) {
        return null;
    }

    @GetMapping(value = "/invoices/{id}")
    public Invoice getInvoiceById(@PathVariable int id) {
        return null;
    }

    @GetMapping(value = "/invoices/customer/{id}")
    public List<Invoice> getInvoicesByCustomerId(@PathVariable int id) {
        return null;
    }

    @GetMapping(value = "/products/inventory")
    public List<Product> getProductsInInventory() {
        return null;
    }

    @GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return null;
    }

    @GetMapping(value = "/products/invoice/{id}")
    public List<Product> getProductByInvoiceId(@PathVariable int id) {
        return null;
    }

    @GetMapping(value = "/levelup/customer/{id}")
    public int getLevelUpPointsByCustomerId(int id) {
        return 0;
    }

    // Admin Endpoints

    @PostMapping(value = "/products")
    public Product createProduct(@RequestBody Product product) {
        return null;
    }

    // getProductById is above in the Retail endpoints

    @GetMapping(value = "/products")
    public List<Product> getAllProducts() {
        return null;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable int id, @RequestBody Product product) {

    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int id) {

    }

}
