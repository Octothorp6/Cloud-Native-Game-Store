package com.trilogyed.retailapi.service;

import com.trilogyed.retailapi.model.LevelUp;
import com.trilogyed.retailapi.util.feign.*;
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
    LevelUpClient levelUpClient;
    ProductClient productClient;

    @Autowired
    public ServiceLayer(CustomerClient customerClient, InventoryClient inventoryClient, InvoiceClient invoiceClient,
                        LevelUpClient levelUpClient, ProductClient productClient) {
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
    }


    //==============================================================================================================
    // CUSTOMER METHODS




    //==============================================================================================================
    // INVENTORY METHODS




    //==============================================================================================================
    // INVOICE METHODS




    //==============================================================================================================
    // LEVEL-UP METHODS




    //==============================================================================================================
    // PRODUCT METHODS



    // FALL-BACK METHOD
    public LevelUp reliable() {
        LevelUp levelUp = new LevelUp();
        return levelUp;
    }

}
