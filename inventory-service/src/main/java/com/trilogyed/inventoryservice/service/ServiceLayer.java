package com.trilogyed.inventoryservice.service;

import com.trilogyed.inventoryservice.dao.InventoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayer {
    InventoryDao inventoryDao;

    @Autowired
    public ServiceLayer(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public void updateItemInventory(int num) {

    }


}
