package org.perscholas.services;

import org.perscholas.dao.IInventoryRepo;
import org.perscholas.models.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    IInventoryRepo inventoryRepo;

    @Autowired
    public InventoryService(IInventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    //Display all inventory
    public List<Inventory> showAllInventory(){ return inventoryRepo.findAll(); }

    //Find inventory by location
    public Inventory showByLocation(String location){
        if (location!=null) {
            Inventory inventory = inventoryRepo.findByiLocation(location);
            return inventory;
        }
        return null;
    }
}
