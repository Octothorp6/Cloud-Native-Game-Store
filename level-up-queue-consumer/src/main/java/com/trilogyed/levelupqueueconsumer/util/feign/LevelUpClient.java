package com.trilogyed.levelupqueueconsumer.util.feign;

import com.trilogyed.levelupqueueconsumer.model.LevelUp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/level-up")
public interface LevelUpClient {
    @PostMapping
    void createLevelUp(@RequestBody LevelUp levelUp);

    @PutMapping
    void updateLevelUp(@RequestBody LevelUp levelUp);
}
