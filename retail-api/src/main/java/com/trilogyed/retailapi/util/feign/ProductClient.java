package com.trilogyed.retailapi.util.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product-service")
public interface ProductClient {

}
