package com.trilogyed.adminapi.util.feign;

import com.trilogyed.adminapi.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "inventory-service")
@RequestMapping(value = "/inventory")
public interface InventoryClient {

    @PostMapping(value = "/admin/inventory")
    public Inventory createInventory(@RequestBody @Valid Inventory inventory);

    @GetMapping(value = "/admin/inventory/{id}")
    public Inventory getInventory(@PathVariable int id);

    @GetMapping(value = "/admin/inventory/product/{id}")
    public Inventory getInventoryByProduct(@PathVariable int id);

    @GetMapping(value = "/admin/inventory")
    public List<Inventory> getAllInventory();

    @PutMapping(value = "/admin/inventory")
    public void updateInventory(@RequestBody @Valid Inventory inventory);

    @DeleteMapping(value = "/admin/inventory/{id}")
    public void deleteInventory(@PathVariable int id);

}
