package com.trilogyed.inventoryservice.service;

import com.trilogyed.inventoryservice.dao.InventoryDao;
import com.trilogyed.inventoryservice.dao.InventoryDaoJdbcTemplateImpl;
import com.trilogyed.inventoryservice.model.Inventory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ServiceLayerTest {
    InventoryDao inventoryDao;
    ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {
        setUpInventoryMock();
        serviceLayer = new ServiceLayer(inventoryDao);
    }

    @Test
    public void saveFindInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(5);
        inventory.setQuantity(10);

        inventory = serviceLayer.saveInventory(inventory);
        Inventory inventory1 = serviceLayer.findInventory(inventory.getInventoryId());
        assertEquals(inventory1, inventory);
    }


    @Test
    public void findAllInventory() {
        List<Inventory> inventories = serviceLayer.findAllInventory();
        assertEquals(1, inventories.size());
    }

    @Test
    public void findInventoryByProduct() {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(1);
        inventory.setProductId(5);
        inventory.setQuantity(10);

        Inventory inventory1 = serviceLayer.findInventoryByProduct(inventory.getProductId());
        assertEquals(inventory1, inventory);
    }

    @Test
    public void updateInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(15);
        inventory.setQuantity(10);
        inventory = serviceLayer.saveInventory(inventory);

        inventory.setQuantity(15);
        serviceLayer.updateInventory(inventory);

        Inventory inventory1 = serviceLayer.findInventory(inventory.getInventoryId());
        assertEquals(inventory1, inventory);
    }

    @Test
    public void deleteInventory() {
        Inventory toDelete = new Inventory();
        toDelete.setInventoryId(66);
        toDelete.setProductId(15);
        toDelete.setQuantity(15);

        serviceLayer.deleteInventory(toDelete.getInventoryId());
        Inventory deleted = serviceLayer.findInventory(toDelete.getInventoryId());
        assertNull(deleted);
    }


    private void setUpInventoryMock() {
        inventoryDao = mock(InventoryDaoJdbcTemplateImpl.class);

        Inventory inventory = new Inventory();
        inventory.setInventoryId(1);
        inventory.setProductId(5);
        inventory.setQuantity(10);

        Inventory inventory1 = new Inventory();
        inventory1.setProductId(5);
        inventory1.setQuantity(10);

        // UPDATE - DELETE
        Inventory toUpdate = new Inventory();
        toUpdate.setInventoryId(5);
        toUpdate.setProductId(15);
        toUpdate.setQuantity(10);

        Inventory inventory2 = new Inventory();
        inventory2.setProductId(15);
        inventory2.setQuantity(10);

        Inventory updated = new Inventory();
        updated.setInventoryId(5);
        updated.setProductId(15);
        updated.setQuantity(15);

        Inventory toDelete = new Inventory();
        toDelete.setInventoryId(66);
        toDelete.setProductId(15);
        toDelete.setQuantity(15);

        List<Inventory> inventoryList = inventoryDao.getAllInventory();
        inventoryList.add(inventory);

        doReturn(inventory).when(inventoryDao).addInventory(inventory1);
        doReturn(toUpdate).when(inventoryDao).addInventory(inventory2);
        doReturn(updated).when(inventoryDao).getInventory(5);
        doReturn(inventory).when(inventoryDao).getInventory(1);
        doReturn(inventory).when(inventoryDao).getInventoryByProductId(5);
        doReturn(inventoryList).when(inventoryDao).getAllInventory();

        doNothing().when(inventoryDao).updateInventoryItem(toUpdate);
        doNothing().when(inventoryDao).deleteInventoryItem(66);
        doReturn(null).when(inventoryDao).getInventory(66);
    }
}