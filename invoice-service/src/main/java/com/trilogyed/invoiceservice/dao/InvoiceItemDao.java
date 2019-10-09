package com.trilogyed.invoiceservice.dao;


import com.trilogyed.invoiceservice.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {
    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);
    InvoiceItem getInvoiceItem(int id);

    List<InvoiceItem> getAllInvoiceItems();
    List<InvoiceItem> getAllInvoiceItemsByInvoice(int invoiceId);

    void updateInvoiceItem(InvoiceItem invoiceItem);
    void deleteInvoiceItem(int id);
}
