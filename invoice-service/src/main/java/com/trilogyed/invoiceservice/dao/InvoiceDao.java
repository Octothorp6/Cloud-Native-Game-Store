package com.trilogyed.invoiceservice.dao;


import com.trilogyed.invoiceservice.model.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice addInvoice(Invoice invoice);
    Invoice getInvoice(int id);

    List<Invoice> getInvoicesByCustomer(int customerId);
    List<Invoice> getAllInvoices();

    void updateInvoice(Invoice invoice);
    void deleteInvoice(int id);
}
