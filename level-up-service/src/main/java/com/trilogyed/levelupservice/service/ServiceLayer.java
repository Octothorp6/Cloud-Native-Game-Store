package com.trilogyed.levelupservice.service;

import com.trilogyed.levelupservice.dao.LevelUpDao;
import com.trilogyed.levelupservice.model.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/*Task:
 *
 */
@Component
public class ServiceLayer {
    private LevelUpDao levelUpDao;

    @Autowired
    public ServiceLayer(LevelUpDao levelUpDao) {
        this.levelUpDao = levelUpDao;
    }

    public List<LevelUp> findAllLevelUps() {
        return levelUpDao.getAllLevelUps();
    }

    public LevelUp findLevelUp(int id) {
        return levelUpDao.getLevelUp(id);
    }

    public LevelUp findLevelUpByCustomer(int customerId) {
        return levelUpDao.getLevelUpByCustomer(customerId);
    }

    public LevelUp saveLevelUp(LevelUp levelUp) {
        return levelUpDao.addLevelUp(levelUp);
    }

    public void updateLevelUp(LevelUp levelUp) {
        levelUpDao.updateLevelUp(levelUp);
    }

    public void deleteLevelUp(int id) {
        levelUpDao.deleteLevelUp(id);
    }
}
