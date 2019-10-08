package com.trilogyed.retailapi.viewmodel;

import com.trilogyed.retailapi.model.Customer;
import com.trilogyed.retailapi.model.InvoiceItem;
import com.trilogyed.retailapi.model.LevelUp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class InvoiceViewModel {
    private int id;
    private LocalDate purchaseDate;
    private BigDecimal total;
    private Customer customer;
    private LevelUp levelUp;
    private List<InvoiceItem> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LevelUp getLevelUp() {
        return levelUp;
    }

    public void setLevelUp(LevelUp levelUp) {
        this.levelUp = levelUp;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return id == that.id &&
                Objects.equals(purchaseDate, that.purchaseDate) &&
                Objects.equals(total, that.total) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(levelUp, that.levelUp) &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchaseDate, total, customer, levelUp, items);
    }
}
