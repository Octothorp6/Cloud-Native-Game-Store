package com.trilogyed.retailapi.util.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {
}
