package com.trilogyed.adminapi.util.feign;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    //Admin API responsible for full CRUD

    
}
