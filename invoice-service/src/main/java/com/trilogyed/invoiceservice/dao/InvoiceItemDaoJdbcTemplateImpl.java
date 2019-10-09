package com.trilogyed.invoiceservice.dao;

import com.trilogyed.invoiceservice.model.InvoiceItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImpl implements InvoiceItemDao {

    @Override
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
        return null;
    }

    @Override
    public InvoiceItem getInvoiceItem(int id) {
        return null;
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return null;
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItemsByInvoice(int invoiceId) {
        return null;
    }

    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {

    }

    @Override
    public void deleteInvoiceItem(int id) {

    }
}
