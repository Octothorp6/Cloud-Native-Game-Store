package com.trilogyed.adminapi.controller;

import com.trilogyed.adminapi.exception.NotFoundException;
import com.trilogyed.adminapi.invoiceViewmodel.InvoiceViewModel;
import com.trilogyed.adminapi.model.Invoice;
import com.trilogyed.adminapi.model.InvoiceItem;
import com.trilogyed.adminapi.service.AdminService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RefreshScope
//@RequestMapping(name = "/admin/invoices")
public class InvoiceController {

    @Autowired
    AdminService adminService;

    public InvoiceController(AdminService adminService){this.adminService = adminService;}

    //==========================================    Invoice    ========================================================

    @RequestMapping(value = "/admin/invoices",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody @Valid Invoice invoice){return adminService.createInvoice(invoice);}

    @RequestMapping(value = "/admin/invoices/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceViewModel getInvoice(@PathVariable int id){
        InvoiceViewModel invoiceFromService = adminService.getInvoice(id);
        if(invoiceFromService == null)
            throw new NotFoundException("No Invoice exists in the DB with given id: "+id);
        return invoiceFromService;
    }

    @RequestMapping(value = "/admin/invoices/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices(){return adminService.getAllInvoices();}

    @RequestMapping(value = "/admin/invoices/customer/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoicesByCustomer(@PathVariable int id){
        return adminService.getAllInvoicesByCustomer(id);
    }

    @RequestMapping(value = "/admin/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody @Valid Invoice invoice){adminService.updateInvoice(invoice);}

//    @DeleteMapping(value = "/{id}")
//    public  void deleteInvoice(@PathVariable int id){adminService.deleteInvoice(id);}

    //==========================================    InvoiceItem    ====================================================

    @RequestMapping(value = "/admin/invoice-items",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceItem createInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem){
       return adminService.createInvoiceItem(invoiceItem);
    }


    @RequestMapping(value = "/admin/invoice-items/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceItem getInvoiceItem(@PathVariable int id){
        InvoiceItem invItemFromService = adminService.getInvoiceItem(id);
        if(invItemFromService == null )
            throw new NotFoundException("No InvoiceItem exists in the DB with given id: "+id);
        return invItemFromService;
    }


    @RequestMapping(value = "/admin/invoice-items/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceItem> getAllInvoiceItems(){return adminService.getAllInvoiceItems();}


    @RequestMapping(value = "/invoice-items", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem){
        adminService.updateInvoiceItem(invoiceItem);
    }

//    @DeleteMapping(value = "/invoice-items/{id}")
//    public void deleteInventory(@PathVariable int id){adminService.deleteInvoiceItem(id);}

//------------------------------------------------------------------------------------------------------------------------
    //Security impl below
    //==========================================    Invoice    ========================================================

//    @RequestMapping(value = "/admin/invoices",method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public InvoiceViewModel createInvoice(Principal principal,@RequestBody @Valid Invoice invoice){return adminService.createInvoice(invoice);}
//
//    @RequestMapping(value = "/admin/invoices/{id}",method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public InvoiceViewModel getInvoice(@PathVariable int id){
//        InvoiceViewModel invoiceFromService = adminService.getInvoice(id);
//        if(invoiceFromService == null)
//            throw new NotFoundException("No Invoice exists in the DB with given id: "+id);
//        return invoiceFromService;
//    }
//
//    @RequestMapping(value = "/admin/invoices/all", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<InvoiceViewModel> getAllInvoices(){return adminService.getAllInvoices();}
//
//    @RequestMapping(value = "/admin/invoices/customer/{id}",method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<InvoiceViewModel> getAllInvoicesByCustomer(@PathVariable int id){
//        return adminService.getAllInvoicesByCustomer(id);
//    }
//
//    @RequestMapping(value = "/admin/invoice", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateInvoice(Principal principal, @RequestBody @Valid Invoice invoice){adminService.updateInvoice(invoice);}

//    @DeleteMapping(value = "/{id}")
//    public  void deleteInvoice(@PathVariable int id){adminService.deleteInvoice(id);}

    //==========================================    InvoiceItem    ====================================================
//
//    @RequestMapping(value = "/admin/invoice-items",method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public InvoiceItem createInvoiceItem(Principal principal,@RequestBody @Valid InvoiceItem invoiceItem){
//        return adminService.createInvoiceItem(invoiceItem);
//    }
//
//
//    @RequestMapping(value = "/admin/invoice-items/{id}",method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public InvoiceItem getInvoiceItem(@PathVariable int id){
//        InvoiceItem invItemFromService = adminService.getInvoiceItem(id);
//        if(invItemFromService == null )
//            throw new NotFoundException("No InvoiceItem exists in the DB with given id: "+id);
//        return invItemFromService;
//    }
//
//
//    @RequestMapping(value = "/admin/invoice-items/all", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<InvoiceItem> getAllInvoiceItems(){return adminService.getAllInvoiceItems();}
//
//
//    @RequestMapping(value = "/invoice-items", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateInvoiceItem(Principal principal,@RequestBody @Valid InvoiceItem invoiceItem){
//        adminService.updateInvoiceItem(invoiceItem);
//    }

}
