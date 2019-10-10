package com.trilogyed.levelupservice.service;

import com.trilogyed.levelupservice.dao.LevelUpDao;
import com.trilogyed.levelupservice.dao.LevelUpDaoJdbcTemplateImpl;
import com.trilogyed.levelupservice.model.LevelUp;
import org.junit.Before;
import org.junit.Test;

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

        List<LevelUp> levelUps = new ArrayList<>();
        levelUps.add(levelUp);

        doReturn(levelUp).when(levelUpDao).addLevelUp(levelUp1);
        doReturn(levelUp).when(levelUpDao).getLevelUp(1);
        doReturn(levelUp).when(levelUpDao).getLevelUpByCustomer(5);
        doReturn(levelUps).when(levelUpDao).getAllLevelUps();
    }

}