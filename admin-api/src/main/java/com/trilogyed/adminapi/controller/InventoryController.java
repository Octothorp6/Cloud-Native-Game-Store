package com.trilogyed.adminapi.controller;

import com.trilogyed.adminapi.exception.NotFoundException;
import com.trilogyed.adminapi.model.Inventory;
import com.trilogyed.adminapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(name = "/admin/inventory")
public class InventoryController {

    @Autowired
    AdminService adminService;

    public InventoryController(AdminService adminService){this.adminService = adminService;}

//===============================================================================
    //Below is the controller w/ security


//
//    //CRUD w/Authorization for Inventory
//
//    @PostMapping
//    public Inventory createInventory(Principal principal, @RequestBody @Valid Inventory inventory){
//        return adminService.createInventory(inventory);
//    }
//
//    @GetMapping(value = "/{id}")
//    public Inventory getInventory( @PathVariable int id){
//        Inventory inventoryFromService = adminService.getInventory(id);
//        if(inventoryFromService==null)
//            throw new NotFoundException("No Inventory exists in the DB with given id: "+id);
//        return inventoryFromService;
//    }
//
//    @GetMapping
//    public List<Inventory> getAllInventory(){return adminService.getAllInventory(); }
//
//    @PutMapping
//    public void updateInventory(Principal principal, @RequestBody @Valid Inventory inventory){
//        adminService.updateInventory(inventory);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public void deleteInventory(Principal principal, @PathVariable int id){adminService.deleteInventory(id);}



    //CRUD w/Authorization for Inventory

    @PostMapping
    public Inventory createInventory(@RequestBody @Valid Inventory inventory){
        return adminService.createInventory(inventory);
    }

    @GetMapping(value = "/{id}")
    public Inventory getInventory( @PathVariable int id){
        Inventory inventoryFromService = adminService.getInventory(id);
        if(inventoryFromService==null)
            throw new NotFoundException("No Inventory exists in the DB with given id: "+id);
        return inventoryFromService;
    }

    @GetMapping
    public List<Inventory> getAllInventory(){return adminService.getAllInventory(); }

    @PutMapping
    public void updateInventory( @RequestBody @Valid Inventory inventory){
        adminService.updateInventory(inventory);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteInventory( @PathVariable int id){adminService.deleteInventory(id);}

}
