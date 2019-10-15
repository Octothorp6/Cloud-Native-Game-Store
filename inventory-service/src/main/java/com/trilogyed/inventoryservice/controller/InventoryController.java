package com.trilogyed.inventoryservice.controller;

import com.trilogyed.inventoryservice.model.Inventory;
import com.trilogyed.inventoryservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping
    public List<Inventory> getAllInventory() {
        return serviceLayer.findAllInventory();
    }

    @PostMapping
    public Inventory createInventory(@RequestBody @Valid Inventory inventory) {
        return serviceLayer.saveInventory(inventory);
    }

    @PutMapping
    public void updateInventory(@RequestBody @Valid Inventory inventory) {
        serviceLayer.updateInventory(inventory);
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
    public void deleteInventory(@PathVariable int id) {
        serviceLayer.deleteInventory(id);
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
