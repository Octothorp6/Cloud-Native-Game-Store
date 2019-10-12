package com.trilogyed.invoiceservice.service;

import com.trilogyed.invoiceservice.dao.InvoiceDao;
import com.trilogyed.invoiceservice.dao.InvoiceItemDao;
import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import com.trilogyed.invoiceservice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    //========================================================================================================
    // INVOICE METHODS

    public InvoiceViewModel saveInvoice(Invoice invoice){
        invoice = invoiceDao.addInvoice(invoice);
        return buildInvoiceViewModel(invoice);
    }

    public InvoiceViewModel findInvoice(int id) {
        Invoice invoice = invoiceDao.getInvoice(id);
        if (invoice == null) {
            return null;
        }
        return buildInvoiceViewModel(invoice);
    }

    public List<InvoiceViewModel> findInvoicesByCustomer(int customerId) {
        List<Invoice> invoices = invoiceDao.getInvoicesByCustomer(customerId);
        if (invoices == null) {
            return null;
        } else {
            List<InvoiceViewModel> invoiceViewModels = new ArrayList<>();

            for (Invoice i : invoices) {
                InvoiceViewModel invoiceViewModel = buildInvoiceViewModel(i);
                invoiceViewModels.add(invoiceViewModel);
            }
            return invoiceViewModels;
        }
    }

    public List<InvoiceViewModel> findAllInvoices() {
        List<Invoice> invoices = invoiceDao.getAllInvoices();
        List<InvoiceViewModel> invoiceViewModels = new ArrayList<>();

        for (Invoice i : invoices) {
            InvoiceViewModel invoiceViewModel = buildInvoiceViewModel(i);
            invoiceViewModels.add(invoiceViewModel);
        }
        return invoiceViewModels;
    }

    public void updateInvoice(Invoice invoice) {
        invoiceDao.updateInvoice(invoice);
    }

    public void removeInvoice(int id) {
        List<InvoiceItem> invoiceItems = findInvoiceItemsByInvoice(id);
        if (invoiceItems.size() != 0) {
            for (InvoiceItem i : invoiceItems) {
                invoiceItemDao.deleteInvoiceItem(i.getInvoiceItemId());
            }
        }
        invoiceDao.deleteInvoice(id);
    }

    //========================================================================================================
    // INVOICE ITEM METHODS

    public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem){
        return invoiceItemDao.addInvoiceItem(invoiceItem);
    }
  
    public InvoiceItem findInvoiceItem(int id) {
        return invoiceItemDao.getInvoiceItem(id);
    }

    public List<InvoiceItem> findInvoiceItemsByInvoice(int invoiceId) {
        return invoiceItemDao.getAllInvoiceItemsByInvoice(invoiceId);
    }

    public List<InvoiceItem> findAllInvoiceItems() {
        return invoiceItemDao.getAllInvoiceItems();
    }

    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    public void removeInvoiceItem(int id) {
        invoiceDao.deleteInvoice(id);
    }

    //=======================================================================================================
    // CALCULATE TOTAL

    private BigDecimal calculateTotal(List<InvoiceItem> invoiceItems) {
        BigDecimal total = BigDecimal.ZERO;

        for (InvoiceItem invoiceItem : invoiceItems) {
            BigDecimal itemTotal;
            itemTotal = invoiceItem.getUnitPrice().multiply(new BigDecimal(invoiceItem.getQuantity()));
            total = total.add(itemTotal);
        }
        return total;
    }


    // HELPER METHOD FOR INVOICEVIEWMODEL
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(invoice.getInvoiceId());
        invoiceViewModel.setCustomerId(invoice.getCustomerId());
        invoiceViewModel.setPurchaseDate(invoice.getPurchaseDate());
        List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItemsByInvoice(invoice.getInvoiceId());
        invoiceViewModel.setInvoiceItems(invoiceItems);
        invoiceViewModel.setTotal(calculateTotal(invoiceItems));

        return invoiceViewModel;
    }

}
