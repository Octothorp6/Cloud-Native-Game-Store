package com.trilogyed.inventoryservice.dao;

import com.trilogyed.inventoryservice.model.Inventory;

import java.util.List;

public interface InventoryDao {
    Inventory addInventory(Inventory inventory);
    Inventory getInventory(int id);
    Inventory getInventoryByProductId(int productId);

    List<Inventory> getAllInventory();

    void updateInventoryItem(Inventory inventory);
    void updateInventoryItem(int num, int productId);
    void deleteInventoryItem(int id);
}
