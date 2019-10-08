package com.trilogyed.retailapi.util.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "inventory-service")
public interface InventoryService {
}
