package com.trilogyed.retailapi.viewmodel;

import com.trilogyed.retailapi.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


public class OrderViewModel {
    private int id;
    private Customer customer;
    private List<InvoiceViewModel> invoices;
    private int points;
    private LocalDate memberDate;


}
