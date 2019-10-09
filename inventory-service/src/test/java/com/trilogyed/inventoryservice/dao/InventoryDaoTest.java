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
    public void addGetDeleteInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(10);
        inventory =  inventoryDao.addInventory(inventory);

        Inventory inventory1 = inventoryDao.getInventory(inventory.getInventoryId());
        assertEquals(inventory1, inventory);

        inventoryDao.deleteInventoryItem(inventory.getInventoryId());
        inventory1 = inventoryDao.getInventory(inventory.getInventoryId());
        assertNull(inventory1);
    }

    @Test
    public void getInventoryByProductId() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(10);
        inventory =  inventoryDao.addInventory(inventory);

        Inventory inventory1 = inventoryDao.getInventoryByProductId(inventory.getProductId());
        assertEquals(inventory1, inventory);
    }

    @Test
    public void getAllInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(10);
        inventory =  inventoryDao.addInventory(inventory);

        List<Inventory> inventories = inventoryDao.getAllInventory();
        assertEquals(1, inventories.size());
    }

    @Test
    public void updateInventoryItem() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(10);
        inventory =  inventoryDao.addInventory(inventory);

        inventory.setQuantity(14);
        inventoryDao.updateInventoryItem(inventory);

        Inventory inventory1 = inventoryDao.getInventory(inventory.getInventoryId());
        assertEquals(inventory1, inventory);
    }

}