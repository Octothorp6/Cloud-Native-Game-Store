package com.trilogyed.retailapi.util.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "customer-service")
public interface CustomerClient {

}
