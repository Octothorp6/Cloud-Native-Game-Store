package com.trilogyed.adminapi.util.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "invoice-service")
@RequestMapping("/invoice")
public interface InvoiceClient {


}
