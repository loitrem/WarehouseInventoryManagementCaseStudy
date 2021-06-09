package org.perscholas.services;

import org.perscholas.dao.IInventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    IInventoryRepo inventoryRepo;

    @Autowired
    public InventoryService(IInventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

}
