package com.trilogyed.inventoryservice.controller;

import com.trilogyed.inventoryservice.model.Inventory;
import com.trilogyed.inventoryservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping
    public List<Inventory> getAllInventory() {
        return serviceLayer.findAllInventory();
    }

    @PostMapping
    public Inventory createInventory(Inventory inventory) {
        return serviceLayer.saveInventory(inventory);
    }

    @GetMapping("/{id}")
    public Inventory getInventory(@PathVariable int id) {
        Inventory inventory = serviceLayer.findInventory(id);
        if (inventory == null) {
            return null;
        }
        return inventory;
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(int id) {
        serviceLayer.deleteInventory(id);
    }

    @PutMapping("/{id}")
    public void updateInventory(int id) {

    }

    @GetMapping("/product/{id}")
    public Inventory getInventoryByProduct(@PathVariable int id) {
        Inventory inventory = serviceLayer.findInventoryByProduct(id);
        if (inventory == null) {
            return null;
        }
        return inventory;
    }

}
