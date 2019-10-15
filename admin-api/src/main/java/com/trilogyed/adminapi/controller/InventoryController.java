package com.trilogyed.adminapi.controller;

import com.trilogyed.adminapi.exception.NotFoundException;
import com.trilogyed.adminapi.model.Inventory;
import com.trilogyed.adminapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RefreshScope
//@RequestMapping(name = "/admin/inventory")
public class InventoryController {

    @Autowired
    AdminService adminService;

    public InventoryController(AdminService adminService){this.adminService = adminService;}


    //CRUD w/Authorization for Inventory

    @RequestMapping(value = "/admin/inventory",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Inventory createInventory(Principal principal,@RequestBody @Valid Inventory inventory){
        return adminService.createInventory(inventory);
    }

    @RequestMapping(value = "/admin/inventory/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Inventory getInventory( @PathVariable int id){
        Inventory inventoryFromService = adminService.getInventory(id);
        if(inventoryFromService==null)
            throw new NotFoundException("No Inventory exists in the DB with given id: "+id);
        return inventoryFromService;
    }


    @RequestMapping(value = "/admin/inventory/product/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Inventory getInventoryByProduct(@PathVariable int id){return adminService.getInventoryByProduct(id);}

    @RequestMapping(value = "/admin/inventory/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Inventory> getAllInventory(){return adminService.getAllInventory(); }

    @RequestMapping(value = "/admin/inventory", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInventory(Principal principal, @RequestBody @Valid Inventory inventory){
        adminService.updateInventory(inventory);
    }

//    @DeleteMapping(value = "/{id}")
//    public void deleteInventory( @PathVariable int id){adminService.deleteInventory(id);}




}
