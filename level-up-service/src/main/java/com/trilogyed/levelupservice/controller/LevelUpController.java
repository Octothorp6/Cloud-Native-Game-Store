package com.trilogyed.levelupservice.controller;

import com.trilogyed.levelupservice.model.LevelUp;
import com.trilogyed.levelupservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/level-up")
public class LevelUpController {
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUp> getAllLevelUps() {
        return serviceLayer.findAllLevelUps();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp createLevelUp(@RequestBody LevelUp levelUp) {
        return serviceLayer.saveLevelUp(levelUp);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLevelUp(@RequestBody LevelUp levelUp) {
        serviceLayer.updateLevelUp(levelUp);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable int id) {
        serviceLayer.deleteLevelUp(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LevelUp getLevelUp(@PathVariable(name = "id") int id) {
        return serviceLayer.findLevelUp(id);
    }

    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public LevelUp getLevelUpByCustomer(@PathVariable(name = "customerId") int customerId) {
        return serviceLayer.findLevelUpByCustomer(customerId);
    }

}
