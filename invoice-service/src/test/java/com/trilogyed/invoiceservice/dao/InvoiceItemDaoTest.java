package com.trilogyed.invoiceservice.dao;

import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoTest {
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {
        // CLEAR INVOICE ITEM DB
        List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItems();
        for (InvoiceItem i : invoiceItems) {
            invoiceItemDao.deleteInvoiceItem(i.getInvoiceItemId());
        }
        // CLEAR INVOICE DB
        List<Invoice> invoices = invoiceDao.getAllInvoices();
        for (Invoice i : invoices) {
            invoiceDao.deleteInvoice(i.getInvoiceId());
        }
    }

    @Test
    public void addGetDeleteInvoiceItem() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 12,11));
        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setUnitPrice(new BigDecimal("20.00"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());
        assertEquals(invoiceItem1, invoiceItem);
    }

    @Test
    public void getAllInvoiceItems() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 12,11));
        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setUnitPrice(new BigDecimal("20.00"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItems();
        assertEquals(1, invoiceItems.size());
    }

    @Test
    public void getInvoiceItemsByInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 12,11));
        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setUnitPrice(new BigDecimal("20.00"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItemsByInvoice(invoiceItem.getInvoiceId());
        assertEquals(1, invoiceItems.size());
    }

    @Test
    public void updateInvoiceItem() {

        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 12,11));
        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setUnitPrice(new BigDecimal("20.00"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        invoiceItem.setQuantity(20);
        invoiceItemDao.updateInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());
        assertEquals(invoiceItem1, invoiceItem);
    }
}