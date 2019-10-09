package com.trilogyed.levelupservice.dao;

import com.trilogyed.levelupservice.model.LevelUp;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class LevelUpDaoJdbcTemplateImpl implements LevelUpDao {

    @Override
    @Transactional
    public LevelUp addLevelUp(LevelUp levelUp) {
        return null;
    }

    @Override
    public LevelUp getLevelUp(int Id) {
        return null;
    }

    @Override
    public LevelUp getLevelUpByCustomer(int customerId) {
        return null;
    }

    @Override
    public List<LevelUp> getAllLevelUps() {
        return null;
    }

    @Override
    public void updateLevelUp(int id) {

    }

    @Override
    public void deleteLevelUp(int id) {

    }
}
