package org.perscholas.services;

import org.perscholas.dao.IInventoryRepo;
import org.perscholas.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public List<Inventory> findAllInventory(){ return inventoryRepo.findAll(); }

    //Find inventory by id
    public Inventory findById(Long id){
        if (id!=0) {
            return inventoryRepo.findByiId(id);
        }
        return null;
    }

    //Find inventory by location
    public Inventory findByLocation(String location){
        if (location!=null) {
            return inventoryRepo.findByiLocation(location);
        }
        return null;
    }

    //find inventory by date received
    public List<Inventory> findByDescription(String description){
        if (description!=null){
            return inventoryRepo.findByiDescription(description);
        }
        return null;
    }

    //find inventory by inventory group
    public List<Inventory> findByInventoryGroup(InventoryGroup groupNumber){
        if (groupNumber!=null){
            return inventoryRepo.findByiInventoryGroup(groupNumber);
        }
        return null;
    }

    //find inventory by status
    public List<Inventory> findByStatus(Status status){
        if (status!=null){
            return inventoryRepo.findByiStatus(status);
        }
        return null;
    }

    //find inventory by item number
    public List<Inventory> findByItemNumber(String itemNumber){
        if (itemNumber!=null){
            return inventoryRepo.findByiItemNumber(itemNumber);
        }
        return null;
    }

    //find inventory by moved by
    public Inventory findByIMovedBy(Employees e){
        if (e!=null){
            return inventoryRepo.findByiMovedBy(e);
        }
        return null;
    }

    //add inventory
    @Transactional
    public void addinventory(Inventory i){
        inventoryRepo.save(i);
    }

    // updates inventory record
    @Transactional
    public void updateInventory(Inventory i){
        inventoryRepo.save(i);
    }

    //Remove inventory
    @Transactional
    public void removeInventory(Long id){
        inventoryRepo.deleteById(id);
    }
}
