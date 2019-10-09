package com.trilogyed.retailapi.service;

import com.trilogyed.retailapi.model.Invoice;
import com.trilogyed.retailapi.util.feign.CustomerClient;
import com.trilogyed.retailapi.util.feign.InventoryClient;
import com.trilogyed.retailapi.util.feign.InvoiceClient;
import com.trilogyed.retailapi.util.feign.ProductClient;
import com.trilogyed.retailapi.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RETAIL SERVICE LAYER
 * PURPOSE: Perform all necessary business logic for all retail purposes.
 * Private methods: buildInvoiceViewModel()
 */


@Component
public class ServiceLayer {
    CustomerClient customerClient;
    InventoryClient inventoryClient;
    InvoiceClient invoiceClient;
    ProductClient productClient;

    @Autowired
    public ServiceLayer(CustomerClient customerClient, InventoryClient inventoryClient, InvoiceClient invoiceClient,
                        ProductClient productClient) {
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.invoiceClient = invoiceClient;
        this.productClient = productClient;
    }


    // CALCULATION METHODS


    // HELPER METHOD FOR VIEWMODEL

}
