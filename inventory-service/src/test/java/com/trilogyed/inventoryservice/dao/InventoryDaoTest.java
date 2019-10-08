package com.trilogyed.inventoryservice.dao;

import com.trilogyed.inventoryservice.model.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InventoryDaoTest {
    @Autowired
    InventoryDao inventoryDao;

    @Before
    public void setUp() throws Exception {
        List<Inventory> inventory = inventoryDao.getAllInventory();
        for (Inventory i : inventory) {
            inventoryDao.deleteInventoryItem(i.getInventoryId());
        }
    }

    @Test
    public void createInventory() {

    }

    @Test
    public void getInventory() {
    }

    @Test
    public void getInventoryByProductId() {
    }

    @Test
    public void getAllInventory() {
    }

    @Test
    public void updateInventoryItem() {
    }

    @Test
    public void deleteInventoryItem() {
    }
}