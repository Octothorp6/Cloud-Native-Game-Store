package com.trilogyed.retailapi.controller;

import com.trilogyed.retailapi.model.Invoice;
import com.trilogyed.retailapi.model.Product;
import com.trilogyed.retailapi.service.ServiceLayer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/gamestore")
public class RetailController {
    private ServiceLayer serviceLayer;
    private RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE = "level-up-exchange";
    public static final String ROUTING_KEY = "level.up.create.level#";

    @Autowired
    public RetailController(ServiceLayer serviceLayer, RabbitTemplate rabbitTemplate) {
        this.serviceLayer = serviceLayer;
        this.rabbitTemplate = rabbitTemplate;
    }

    // RETAIL ENDPOINTS
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

    @GetMapping(value = "/products")
    public List<Product> getAllProducts() {
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
    public List<Product> getProductsByInvoiceId(@PathVariable int id) {
        return null;
    }

    @GetMapping(value = "/levelup/customer/{id}")
    public int getLevelUpPointsByCustomerId(int id) {
        return 0;
    }


}
