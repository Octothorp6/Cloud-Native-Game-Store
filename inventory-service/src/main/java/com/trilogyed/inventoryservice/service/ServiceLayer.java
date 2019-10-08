package com.trilogyed.inventoryservice.service;

import com.trilogyed.inventoryservice.dao.InventoryDao;
import com.trilogyed.inventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayer {
    InventoryDao inventoryDao;

    @Autowired
    public ServiceLayer(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryDao.addInventory(inventory);
    }

    public Inventory findInventory(int id) {
        return inventoryDao.getInventory(id);
    }

    public Inventory findInventoryByProduct(int id) {
        return inventoryDao.getInventoryByProductId(id);
    }

    public void deleteInventoryItem(int id) {
        inventoryDao.deleteInventoryItem(id);
    }

}
