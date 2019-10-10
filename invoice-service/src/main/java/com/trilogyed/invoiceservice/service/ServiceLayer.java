package com.trilogyed.invoiceservice.service;

import com.trilogyed.invoiceservice.dao.InvoiceDao;
import com.trilogyed.invoiceservice.dao.InvoiceItemDao;
import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import com.trilogyed.invoiceservice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;

    @Autowired
    public ServiceLayer(InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao){
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }


    // INVOICE METHODS
    public Invoice saveInvoice(Invoice invoice){
        return invoiceDao.addInvoice(invoice);
    }

    public InvoiceViewModel getInvoice(int id) {
        Invoice invoice = invoiceDao.getInvoice(id);
        if (invoice == null) {
            return null;
        }
        return buildInvoiceViewModel(invoice);
    }

    public InvoiceViewModel getInvoiceByCustomer(int customerId) {
        Invoice invoice = invoiceDao.getInvoiceByCustomer(customerId);
        if (invoice == null) {
            return null;
        }
        return buildInvoiceViewModel(invoice);
    }

    public void updateInvoice(Invoice invoice) {
        invoiceDao.updateInvoice(invoice);
    }

    public void removeInvoice(int id) {
        invoiceDao.deleteInvoice(id);
    }

    //========================================================================================================
    //========================================================================================================

    // INVOICE ITEM METHODS
    public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem){
        return invoiceItemDao.addInvoiceItem(invoiceItem);
    }

    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    public void removeInvoiceItem(int id) {
        invoiceDao.deleteInvoice(id);
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItemsByInvoice(invoice.getInvoiceId());

        return invoiceViewModel;
    }

}
