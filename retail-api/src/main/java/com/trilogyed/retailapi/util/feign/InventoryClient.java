package com.trilogyed.retailapi.util.feign;

import com.trilogyed.retailapi.model.Inventory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "inventory-service")
@RequestMapping(value = "/inventory")
public interface InventoryClient {
    @GetMapping
    List<Inventory> getAllInventory();

    @PostMapping
    Inventory createInventory(Inventory inventory);

    @PutMapping
    void updateInventory(@RequestBody Inventory inventory);

    @GetMapping("/{id}")
    Inventory getInventory(@PathVariable int id);

    @DeleteMapping("/{id}")
    void deleteInventory(@PathVariable int id);

    @GetMapping("/product/{id}")
    Inventory getInventoryByProduct(@PathVariable int id);
}
