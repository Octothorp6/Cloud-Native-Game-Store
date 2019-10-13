package com.trilogyed.levelupservice.service;

import com.trilogyed.levelupservice.dao.LevelUpDao;
import com.trilogyed.levelupservice.dao.LevelUpDaoJdbcTemplateImpl;
import com.trilogyed.levelupservice.model.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ServiceLayerTest {
    LevelUpDao levelUpDao;
    ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {
        setLevelUpMock();
        serviceLayer = new ServiceLayer(levelUpDao);
    }


    @Test
    public void saveFindLevelUp() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(5);
        levelUp.setPoints(100);
        levelUp.setMemberDate(LocalDate.of(2019,11,10));
        levelUp = serviceLayer.saveLevelUp(levelUp);

        LevelUp levelUp1 = serviceLayer.findLevelUp(levelUp.getLevelUpId());
        assertEquals(levelUp1, levelUp);
    }

    @Test
    public void findAllLevelUps() {
        List<LevelUp> levelUps = serviceLayer.findAllLevelUps();
        assertEquals(1, levelUps.size());
    }

    @Test
    public void updateLevelUp() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(10);
        levelUp.setPoints(300);
        levelUp.setMemberDate(LocalDate.of(2019,11,10));
        levelUp = levelUpDao.addLevelUp(levelUp);

        levelUp.setPoints(500);
        levelUpDao.updateLevelUp(levelUp);

        LevelUp updated = serviceLayer.findLevelUp(levelUp.getLevelUpId());
        assertEquals(updated, levelUp);
    }


    @Test
    public void deleteLevelUp() {
        LevelUp toDelete = new LevelUp();
        toDelete.setLevelUpId(50);
        toDelete.setCustomerId(90);
        toDelete.setPoints(300);
        toDelete.setMemberDate(LocalDate.of(2019,11,11));
        serviceLayer.deleteLevelUp(toDelete.getLevelUpId());

        LevelUp levelUp = serviceLayer.findLevelUp(toDelete.getLevelUpId());
        assertNull(levelUp);
    }

    @Test
    public void findLevelUpByCustomer() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(5);
        levelUp.setPoints(100);
        levelUp.setMemberDate(LocalDate.of(2019,11,10));
        levelUp = serviceLayer.saveLevelUp(levelUp);

        LevelUp levelUp1 = serviceLayer.findLevelUpByCustomer(levelUp.getCustomerId());
        assertEquals(levelUp1, levelUp);
    }


    private void setLevelUpMock() {
        levelUpDao = mock(LevelUpDaoJdbcTemplateImpl.class);

        LevelUp levelUp = new LevelUp();
        levelUp.setLevelUpId(1);
        levelUp.setCustomerId(5);
        levelUp.setPoints(100);
        levelUp.setMemberDate(LocalDate.of(2019,11,10));

        LevelUp levelUp1 = new LevelUp();
        levelUp1.setCustomerId(5);
        levelUp1.setPoints(100);
        levelUp1.setMemberDate(LocalDate.of(2019,11,10));

        //UPDATES/DELETE
        LevelUp levelUp2 = new LevelUp();
        levelUp2.setLevelUpId(5);
        levelUp2.setCustomerId(10);
        levelUp2.setPoints(300);
        levelUp2.setMemberDate(LocalDate.of(2019,11,10));

        LevelUp levelUp3 = new LevelUp();
        levelUp3.setCustomerId(10);
        levelUp3.setPoints(300);
        levelUp3.setMemberDate(LocalDate.of(2019,11,10));

        LevelUp updated = new LevelUp();
        updated.setLevelUpId(5);
        updated.setCustomerId(10);
        updated.setPoints(500);
        updated.setMemberDate(LocalDate.of(2019,11,10));

        LevelUp deleted = new LevelUp();
        deleted.setLevelUpId(50);
        deleted.setCustomerId(90);
        deleted.setPoints(300);
        deleted.setMemberDate(LocalDate.of(2019,11,11));


        List<LevelUp> levelUps = new ArrayList<>();
        levelUps.add(levelUp);

        doReturn(levelUp).when(levelUpDao).addLevelUp(levelUp1);
        doReturn(levelUp).when(levelUpDao).getLevelUp(1);
        doReturn(updated).when(levelUpDao).getLevelUp(5);
        doReturn(levelUp2).when(levelUpDao).addLevelUp(levelUp3);
        doReturn(levelUp).when(levelUpDao).getLevelUpByCustomer(5);
        doReturn(levelUps).when(levelUpDao).getAllLevelUps();
        doNothing().when(levelUpDao).updateLevelUp(levelUp2);
        doNothing().when(levelUpDao).deleteLevelUp(deleted.getLevelUpId());
        doReturn(null).when(levelUpDao).getLevelUp(deleted.getLevelUpId());

    }

}