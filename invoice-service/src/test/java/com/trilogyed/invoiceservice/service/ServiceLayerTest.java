package com.trilogyed.invoiceservice.service;

import com.trilogyed.invoiceservice.dao.InvoiceDao;
import com.trilogyed.invoiceservice.dao.InvoiceDaoJdbcTemplateImpl;
import com.trilogyed.invoiceservice.dao.InvoiceItemDao;
import com.trilogyed.invoiceservice.dao.InvoiceItemDaoJdbcTemplateImpl;
import com.trilogyed.invoiceservice.model.Invoice;
import com.trilogyed.invoiceservice.model.InvoiceItem;
import com.trilogyed.invoiceservice.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ServiceLayerTest {
    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;
    private ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {
        setUpInvoiceMock();
        setUpInvoiceItemMock();
        serviceLayer = new ServiceLayer(invoiceDao, invoiceItemDao);
    }

    @Test
    public void saveFindInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2019,11,12));

        InvoiceViewModel invoiceViewModel = serviceLayer.saveInvoice(invoice);
        InvoiceViewModel invoiceViewModel1 = serviceLayer.findInvoice(invoiceViewModel.getId());

        assertEquals(invoiceViewModel1, invoiceViewModel);
    }

    @Test
    public void findInvoicesByCustomer() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2019,11,12));

        InvoiceViewModel invoiceViewModel = serviceLayer.saveInvoice(invoice);

        List<InvoiceViewModel> invoicesByCustomer = serviceLayer.findInvoicesByCustomer(invoice.getCustomerId());
        assertEquals(1, invoicesByCustomer.size());
    }

    @Test
    public void findAllInvoices() {
        List<InvoiceViewModel> invoices = serviceLayer.findAllInvoices();
        assertEquals(1, invoices.size());
    }

    @Test
    public void updateInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(8);
        invoice.setPurchaseDate(LocalDate.of(2019,11,12));

        InvoiceViewModel invoiceViewModel = serviceLayer.saveInvoice(invoice);

        invoice.setPurchaseDate(LocalDate.of(2019,11,14));
        serviceLayer.updateInvoice(invoice);

        invoiceViewModel.setPurchaseDate(invoice.getPurchaseDate());

        InvoiceViewModel invoiceViewModel1 = serviceLayer.findInvoice(invoiceViewModel.getId());
        assertEquals(invoiceViewModel1, invoiceViewModel);
    }

    @Test
    public void deleteInvoice() {
        serviceLayer.removeInvoice(66);
        InvoiceViewModel invoiceViewModel = serviceLayer.findInvoice(66);
        assertNull(invoiceViewModel);
    }

    @Test
    public void saveFindInvoiceItem() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setUnitPrice(new BigDecimal("20.00"));
        invoiceItem = serviceLayer.saveInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = serviceLayer.findInvoiceItem(invoiceItem.getInvoiceItemId());
        assertEquals(invoiceItem1, invoiceItem);
    }

    @Test
    public void findAllInvoiceItems() {
        List<InvoiceItem> invoiceItems = serviceLayer.findAllInvoiceItems();
        assertEquals(1,invoiceItems.size());
    }

    @Test
    public void updateInvoiceItem() {
        InvoiceItem toUpdate = new InvoiceItem();
        toUpdate.setInvoiceId(3);
        toUpdate.setInventoryId(1);
        toUpdate.setQuantity(10);
        toUpdate.setUnitPrice(new BigDecimal("20.00"));
        toUpdate = serviceLayer.saveInvoiceItem(toUpdate);

        toUpdate.setQuantity(15);
        serviceLayer.updateInvoiceItem(toUpdate);

        InvoiceItem invoiceItem = serviceLayer.findInvoiceItem(toUpdate.getInvoiceItemId());
        assertEquals(invoiceItem, toUpdate);
    }


    @Test
    public void deleteInvoiceItem() {

        InvoiceItem toDelete = new InvoiceItem();
        toDelete.setInvoiceItemId(77);
        toDelete.setInvoiceId(30);
        toDelete.setInventoryId(4);
        toDelete.setQuantity(10);
        toDelete.setUnitPrice(new BigDecimal("20.00"));

        serviceLayer.removeInvoiceItem(toDelete.getInvoiceItemId());
        InvoiceItem invoiceItem = serviceLayer.findInvoiceItem(toDelete.getInvoiceItemId());
        assertNull(invoiceItem);
    }

    //======================================================================================================
    // SETUP METHODS
    private void setUpInvoiceMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2019,11,12));

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(5);
        invoice1.setPurchaseDate(LocalDate.of(2019,11,12));

        // USED FOR UPDATE
        Invoice toUpdate = new Invoice();
        toUpdate.setInvoiceId(4);
        toUpdate.setCustomerId(8);
        toUpdate.setPurchaseDate(LocalDate.of(2019,11,12));

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(8);
        invoice2.setPurchaseDate(LocalDate.of(2019,11,12));

        Invoice updated = new Invoice();
        updated.setInvoiceId(4);
        updated.setCustomerId(8);
        updated.setPurchaseDate(LocalDate.of(2019,11,14));


        // USED FOR DELETE
        Invoice toDelete = new Invoice();
        toDelete.setInvoiceId(66);
        toDelete.setCustomerId(8);
        toDelete.setPurchaseDate(LocalDate.of(2019,11,12));

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);


        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(toUpdate).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        doReturn(updated).when(invoiceDao).getInvoice(4);
        doReturn(invoices).when(invoiceDao).getInvoicesByCustomer(5);
        doReturn(invoices).when(invoiceDao).getAllInvoices();

        doNothing().when(invoiceDao).updateInvoice(invoice2);
        doNothing().when(invoiceDao).deleteInvoice(toDelete.getInvoiceId());
        doReturn(null).when(invoiceDao).getInvoice(66);
    }

    private void setUpInvoiceItemMock() {
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImpl.class);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(2);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setUnitPrice(new BigDecimal("20.00"));

        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceId(1);
        invoiceItem1.setInventoryId(1);
        invoiceItem1.setQuantity(10);
        invoiceItem1.setUnitPrice(new BigDecimal("20.00"));


        // UPDATE - DELETE
        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoiceItemId(5);
        invoiceItem2.setInvoiceId(3);
        invoiceItem2.setInventoryId(1);
        invoiceItem2.setQuantity(10);
        invoiceItem2.setUnitPrice(new BigDecimal("20.00"));

        InvoiceItem toUpdate = new InvoiceItem();
        toUpdate.setInvoiceId(3);
        toUpdate.setInventoryId(1);
        toUpdate.setQuantity(10);
        toUpdate.setUnitPrice(new BigDecimal("20.00"));

        InvoiceItem updated = new InvoiceItem();
        updated.setInvoiceItemId(5);
        updated.setInvoiceId(3);
        updated.setInventoryId(1);
        updated.setQuantity(15);
        updated.setUnitPrice(new BigDecimal("20.00"));


        InvoiceItem toDelete = new InvoiceItem();
        toDelete.setInvoiceItemId(77);
        toDelete.setInvoiceId(30);
        toDelete.setInventoryId(4);
        toDelete.setQuantity(10);
        toDelete.setUnitPrice(new BigDecimal("20.00"));

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        doReturn(invoiceItem).when(invoiceItemDao).addInvoiceItem(invoiceItem1);
        doReturn(invoiceItem2).when(invoiceItemDao).addInvoiceItem(toUpdate);
        doReturn(invoiceItem).when(invoiceItemDao).getInvoiceItem(2);
        doReturn(updated).when(invoiceItemDao).getInvoiceItem(5);
        doReturn(invoiceItems).when(invoiceItemDao).getAllInvoiceItemsByInvoice(1);
        doReturn(invoiceItems).when(invoiceItemDao).getAllInvoiceItems();

        doNothing().when(invoiceItemDao).updateInvoiceItem(invoiceItem);
        doNothing().when(invoiceItemDao).deleteInvoiceItem(77);
        doReturn(null).when(invoiceItemDao).getInvoiceItem(77);
    }
}