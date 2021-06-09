package org.perscholas.services;

import org.perscholas.dao.IInventoryRepo;
import org.perscholas.models.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
            return inventoryRepo.findByiLocation(location);
        }
        return null;
    }

    //find inventory by date received
    public Optional<Inventory> showByDateReceived(Date date){
        if (date!=null){
            Optional<Inventory> inventory = inventoryRepo.findByiDateReceived(date);
        }
        return Optional.empty();
    }

    //find inventory by inventory group
    public Optional<Inventory> showByIventoryGroup(int groupNumber){
        if (groupNumber!=0){
            return inventoryRepo.findByiInventoryGroup(groupNumber);
        }
        return Optional.empty();
    }

    //find inventory by status
    public Optional<Inventory> showByStatus(String status){
        if (status!=null){
            return inventoryRepo.findByiStatus(status);
        }
        return Optional.empty();
    }

    //find inventory by item number
    public Optional<Inventory> showByItemNumber(int itemNumber){
        if (itemNumber!=0){
            return inventoryRepo.findByiItemNumber(itemNumber);
        }
        return Optional.empty();
    }
}
