package com.trilogyed.retailapi.util.feign;

import com.trilogyed.retailapi.model.Invoice;
import com.trilogyed.retailapi.viewmodel.InvoiceViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {
    @PostMapping
    InvoiceViewModel createInvoice(Invoice invoice);
}
