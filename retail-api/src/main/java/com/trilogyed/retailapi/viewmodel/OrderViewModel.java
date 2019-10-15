package com.trilogyed.retailapi.viewmodel;

import com.trilogyed.retailapi.model.Customer;

import java.time.LocalDate;
import java.util.Objects;


public class OrderViewModel {
    private int id;
    private int levelUpPoints;
    private LocalDate purchaseDate;
    private Customer customer;
    private InvoiceViewModel invoice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevelUpPoints() {
        return levelUpPoints;
    }

    public void setLevelUpPoints(int levelUpPoints) {
        this.levelUpPoints = levelUpPoints;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public InvoiceViewModel getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceViewModel invoice) {
        this.invoice = invoice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderViewModel that = (OrderViewModel) o;
        return id == that.id &&
                levelUpPoints == that.levelUpPoints &&
                Objects.equals(purchaseDate, that.purchaseDate) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(invoice, that.invoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, levelUpPoints, purchaseDate, customer, invoice);
    }
}
