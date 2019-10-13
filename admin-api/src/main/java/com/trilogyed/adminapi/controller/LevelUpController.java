package com.trilogyed.adminapi.controller;

import com.trilogyed.adminapi.exception.NotFoundException;
import com.trilogyed.adminapi.model.LevelUp;
import com.trilogyed.adminapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@RefreshScope
public class LevelUpController {
    @Autowired
    AdminService adminService;

    public LevelUpController(AdminService adminService) { this.adminService = adminService;  }


    @RequestMapping(value = "/admin/level-ups",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public LevelUp createLevelUp(@RequestBody @Valid LevelUp levelUp){
        return adminService.createLevelUp(levelUp);
    }

    @RequestMapping(value = "/admin/level-ups/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public LevelUp getLevelUp(@PathVariable int id){
        LevelUp levelUpFromService = adminService.getLevelUp(id);
        if(levelUpFromService==null)
            throw new NotFoundException("No levelup exists in the DB with given id: "+id);
        return levelUpFromService;
    }
    @RequestMapping(value = "/admin/level-ups/customer/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public LevelUp getLevelUpByCustomer(@PathVariable int id){
        LevelUp levelUpFromService = adminService.getLevelUpByCustomer(id);
        if(levelUpFromService==null)
            throw new NotFoundException("No levelup exists in the DB with given id: "+id);
        return levelUpFromService;
    }

    @RequestMapping(value = "/admin/level-ups/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<LevelUp> getLevelUps(){
        return adminService.getLevelUps();
    }

    @RequestMapping(value = "/admin/level-up", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLevelUp( @RequestBody @Valid LevelUp levelUp){
        adminService.updateLevelUp(levelUp);
    }

//    @DeleteMapping(value = "/{id}")
//    public void deleteLevelUp(@PathVariable int id){
//        adminService.deleteLevelUp(id);
//    }




    //CRUD w/ Authorization for LevelUp
//    @PostMapping
//    public LevelUp createLevelUp(Principal principal, @RequestBody @Valid LevelUp levelUp){
//        return adminService.createLevelUp(levelUp);
//    }
//
//    @GetMapping(value = "/{id}")
//    public LevelUp getLevelUp(@PathVariable int id){
//        LevelUp levelUpFromService = adminService.getLevelUp(id);
//        if(levelUpFromService==null)
//            throw new NotFoundException("No customer exists in the DB with given id: "+id);
//        return levelUpFromService;
//    }
//
//    @GetMapping
//    public List<LevelUp> getLevelUps(){
//        return adminService.getLevelUps();
//    }
//
//    @PutMapping
//    public void updateLevelUp(Principal principal, @RequestBody @Valid LevelUp levelUp){
//        adminService.updateLevelUp(levelUp);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public void deleteLevelUp(Principal principal, @PathVariable int id){
//        adminService.deleteLevelUp(id);
//    }
}
