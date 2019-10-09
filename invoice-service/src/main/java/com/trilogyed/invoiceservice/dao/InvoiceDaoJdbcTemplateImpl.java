package com.trilogyed.invoiceservice.dao;

import com.trilogyed.invoiceservice.model.Invoice;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public Invoice getInvoice(int id) {
        return null;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return null;
    }

    @Override
    public void updateInvoice(Invoice invoice) {

    }

    @Override
    public void deleteInvoice(int id) {

    }
}
