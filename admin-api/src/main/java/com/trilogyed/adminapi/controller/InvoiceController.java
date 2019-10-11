package com.trilogyed.adminapi.controller;

import com.trilogyed.adminapi.exception.NotFoundException;
import com.trilogyed.adminapi.model.Invoice;
import com.trilogyed.adminapi.model.InvoiceItem;
import com.trilogyed.adminapi.service.AdminService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(name = "/admin/invoices")
public class InvoiceController {

    @Autowired
    AdminService adminService;

    public InvoiceController(AdminService adminService){this.adminService = adminService;}
    //==========================================    Invoice    ========================================================

    @PostMapping
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice){return adminService.createInvoice(invoice);}

    @GetMapping(value = "/{id}")
    public Invoice getInvoice(@PathVariable int id){
        Invoice invoiceFromService = adminService.getInvoice(id);
        if(invoiceFromService == null)
            throw new NotFoundException("No Invoice exists in the DB with given id: "+id);
        return invoiceFromService;
    }

    @GetMapping
    public List<Invoice> getAllInvoices(){return adminService.getAllInvoices();}

    @PutMapping
    public void updateInvoice(@RequestBody @Valid Invoice invoice){adminService.updateInvoice(invoice);}

    @DeleteMapping(value = "/{id}")
    public  void deleteInvoice(@PathVariable int id){adminService.deleteInvoice(id);}

    //==========================================    InvoiceItem    ====================================================

    @PostMapping(value = "/invoice-items")
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem){
       return adminService.createInvoiceItem(invoiceItem);
    }

    @GetMapping(value = "/invoice-items/{id}")
    public InvoiceItem getInvoiceItem(@PathVariable int id){
        InvoiceItem invItemFromService = adminService.getInvoiceItem(id);
        if(invItemFromService == null )
            throw new NotFoundException("No InvoiceItem exists in the DB with given id: "+id);
        return invItemFromService;
    }

    @GetMapping(value = "/invoice-items")
    public List<InvoiceItem> getAllInvoiceItems(){return adminService.getAllInvoiceItems();}

    @PutMapping(value = "/invoice-items")
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem){
        adminService.updateInvoiceItem(invoiceItem);
    }

    @DeleteMapping(value = "/invoice-items/{id}")
    public void deleteInventory(@PathVariable int id){adminService.deleteInvoiceItem(id);}


    //Security impl below
    //==========================================    Invoice    ========================================================

//    @PostMapping
//    public Invoice createInvoice(Principal principal, @RequestBody @Valid Invoice invoice){return adminService.createInvoice(invoice);}
//
//    @GetMapping(value = "/{id}")
//    public Invoice getInvoice(@PathVariable int id){
//        Invoice invoiceFromService = adminService.getInvoice(id);
//        if(invoiceFromService == null)
//            throw new NotFoundException("No Invoice exists in the DB with given id: "+id);
//        return invoiceFromService;
//    }
//
//    @GetMapping
//    public List<Invoice> getAllInvoices(){return adminService.getAllInvoices();}
//
//    @PutMapping
//    public void updateInvoice(Principal principal,@RequestBody @Valid Invoice invoice){adminService.updateInvoice(invoice);}
//
//    @DeleteMapping(value = "/{id}")
//    public  void deleteInvoice(Principal principal,@PathVariable int id){adminService.deleteInvoice(id);}
//
//    //==========================================    InvoiceItem    ====================================================
//
//    @PostMapping(value = "/invoice-items")
//    public InvoiceItem createInvoiceItem(Principal principal,@RequestBody @Valid InvoiceItem invoiceItem){
//        return adminService.createInvoiceItem(invoiceItem);
//    }
//
//    @GetMapping(value = "/invoice-items/{id}")
//    public InvoiceItem getInvoiceItem(@PathVariable int id){
//        InvoiceItem invItemFromService = adminService.getInvoiceItem(id);
//        if(invItemFromService == null )
//            throw new NotFoundException("No InvoiceItem exists in the DB with given id: "+id);
//        return invItemFromService;
//    }
//
//    @GetMapping(value = "/invoice-items")
//    public List<InvoiceItem> getAllInvoiceItems(){return adminService.getAllInvoiceItems();}
//
//    @PutMapping(value = "/invoice-items")
//    public void updateInvoiceItem(Principal principal,@RequestBody @Valid InvoiceItem invoiceItem){
//        adminService.updateInvoiceItem(invoiceItem);
//    }
//
//    @DeleteMapping(value = "/invoice-items/{id}")
//    public void deleteInventory(Principal principal,@PathVariable int id){adminService.deleteInvoiceItem(id);}

}
