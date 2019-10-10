package com.trilogyed.retailapi.util.feign;


import com.trilogyed.retailapi.model.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "level-up-service")
@RequestMapping(value = "/level-up")
public interface LevelUpClient {
    @GetMapping
    List<LevelUp> getAllLevelUps();

    @DeleteMapping("/{id}")
    void deleteLevelUp(@PathVariable int id);

    @GetMapping("/{id}")
    LevelUp getLevelUp(@PathVariable(name = "id") int id);

    @GetMapping("/customer/{customerId}")
    LevelUp getLevelUpByCustomer(@PathVariable(name = "customerId") int customerId);
}
