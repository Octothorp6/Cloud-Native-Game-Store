package com.trilogyed.levelupservice.dao;

import com.trilogyed.levelupservice.model.LevelUp;

import java.util.List;


public interface LevelUpDao {
    LevelUp addLevelUp(LevelUp levelUp);
    LevelUp getLevelUp(int Id);
    LevelUp getLevelUpByCustomer(int customerId);

    List<LevelUp> getAllLevelUps();

    void updateLevelUp(int id);
    void deleteLevelUp(int id);
}
