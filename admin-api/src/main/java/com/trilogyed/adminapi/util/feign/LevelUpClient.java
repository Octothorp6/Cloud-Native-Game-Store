package com.trilogyed.adminapi.util.feign;

import com.trilogyed.adminapi.model.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "level-up-service")
@RequestMapping("/level-ups")
public interface LevelUpClient {

    @PostMapping
    public LevelUp createLevelUp(@RequestBody @Valid LevelUp levelUp);

    @GetMapping(value = "/{id}")
    public LevelUp getLevelUp(@PathVariable int id);

    @GetMapping("/customer/{customerId}")
    public LevelUp getLevelUpByCustomer(@PathVariable(name = "customerId") int customerId);


    @GetMapping
    public List<LevelUp> getLevelUps();

    @PutMapping
    public void updateLevelUp(@RequestBody @Valid LevelUp levelUp);

    @DeleteMapping(value = "/{id")
    public void deleteLevelUp(@PathVariable int id);
}
