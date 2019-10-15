package com.trilogyed.levelupservice.controller;

import com.trilogyed.levelupservice.model.LevelUp;
import com.trilogyed.levelupservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/level-up")
public class LevelUpController {
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping
    public List<LevelUp> getAllLevelUps() {
        return serviceLayer.findAllLevelUps();
    }

    @PostMapping
    public LevelUp createLevelUp(@RequestBody LevelUp levelUp) {
        return serviceLayer.saveLevelUp(levelUp);
    }

    @PutMapping
    public void updateLevelUp(@RequestBody LevelUp levelUp) {
        serviceLayer.updateLevelUp(levelUp);
    }

    @DeleteMapping("/{id}")
    public void deleteLevelUp(@PathVariable int id) {
        serviceLayer.deleteLevelUp(id);
    }

    @GetMapping("/{id}")
    public LevelUp getLevelUp(@PathVariable(name = "id") int id) {
        return serviceLayer.findLevelUp(id);
    }

    @GetMapping("/customer/{customerId}")
    public LevelUp getLevelUpByCustomer(@PathVariable(name = "customerId") int customerId) {
        return serviceLayer.findLevelUpByCustomer(customerId);
    }

}
