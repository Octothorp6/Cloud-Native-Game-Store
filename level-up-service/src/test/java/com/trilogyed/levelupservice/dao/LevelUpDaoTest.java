package com.trilogyed.levelupservice.dao;

import com.trilogyed.levelupservice.model.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LevelUpDaoTest {
    @Autowired
    LevelUpDao levelUpDao;

    @Before
    public void setUp() throws Exception {
        List<LevelUp> levelUps = levelUpDao.getAllLevelUps();
        for (LevelUp l : levelUps) {
            levelUpDao.deleteLevelUp(l.getLevelUpId());
        }
    }

    @Test
    public void addGetDeleteLevelUp() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(200);
        levelUp.setMemberDate(LocalDate.of(2019,11,11));
        levelUp = levelUpDao.addLevelUp(levelUp);

        LevelUp levelUp1 = levelUpDao.getLevelUp(levelUp.getLevelUpId());
        assertEquals(levelUp1, levelUp);
    }

    @Test
    public void getLevelUpByCustomer() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(200);
        levelUp.setMemberDate(LocalDate.of(2019,11,11));
        levelUp = levelUpDao.addLevelUp(levelUp);

        LevelUp levelUp1 = levelUpDao.getLevelUpByCustomer(levelUp.getCustomerId());
        assertEquals(levelUp1, levelUp);
    }

    @Test
    public void getAllLevelUps() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(200);
        levelUp.setMemberDate(LocalDate.of(2019,11,11));
        levelUp = levelUpDao.addLevelUp(levelUp);

        List<LevelUp> levelUps = levelUpDao.getAllLevelUps();
        assertEquals(1, levelUps.size());
    }

    @Test
    public void updateLevelUp() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(200);
        levelUp.setMemberDate(LocalDate.of(2019,11,11));
        levelUp = levelUpDao.addLevelUp(levelUp);

        levelUp.setPoints(400);
        levelUpDao.updateLevelUp(levelUp);

        LevelUp levelUp1 = levelUpDao.getLevelUp(levelUp.getLevelUpId());
        assertEquals(levelUp1, levelUp);
    }
}