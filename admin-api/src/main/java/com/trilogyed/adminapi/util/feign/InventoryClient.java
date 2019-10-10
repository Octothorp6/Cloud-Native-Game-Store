package com.trilogyed.adminapi.util.feign;

import com.trilogyed.adminapi.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "inventory-service")
@RequestMapping(value = "/inventory")
public interface InventoryClient {

    @PostMapping
    public Inventory createInventory(@RequestBody @Valid Inventory inventory);

    @GetMapping(value = "/{id}")
    public Inventory getInventory(@PathVariable int id);

    @GetMapping
    public List<Inventory> getAllInventory();

    @PutMapping
    public void updateInventory(@RequestBody @Valid Inventory inventory);

    @DeleteMapping(value = "/{id}")
    public void deleteInventory(@PathVariable int id);

}
