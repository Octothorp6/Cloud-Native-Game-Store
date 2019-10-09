package com.trilogyed.retailapi.util.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {
}
