package org.perscholas.services;

import org.perscholas.dao.IInventoryGroupRepo;
import org.perscholas.dao.IInventoryRepo;
import org.perscholas.models.InventoryGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryGroupService {

    IInventoryGroupRepo iInventoryGroupRepo;

    @Autowired
    public InventoryGroupService(IInventoryGroupRepo iInventoryGroupRepo) {
        this.iInventoryGroupRepo = iInventoryGroupRepo;
    }

    //find all inventory groups
    public List<InventoryGroup> findAllInventoryGroup(){
        return iInventoryGroupRepo.findAll();
    }

    //find inventory group by id
    public Optional<InventoryGroup> findById(int id){
        if (id != 0){
            return iInventoryGroupRepo.findBygId(id);
        }
        return Optional.empty();
    }

    //find inventory group by company name
    public Optional<InventoryGroup> findByCompanyName(String companyName){
        if (companyName != null){
            return iInventoryGroupRepo.findBygCompanyName(companyName);
        }
        return Optional.empty();
    }
}
