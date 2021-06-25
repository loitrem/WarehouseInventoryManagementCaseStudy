package org.perscholas.services;

import org.perscholas.dao.IInventoryGroupRepo;
import org.perscholas.models.InventoryGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public InventoryGroup findById(Long id){
        if (id != 0){
            return iInventoryGroupRepo.findBygId(id);
        }
        return null;
    }

    //find inventory group by company name
    public List<InventoryGroup> findByCompanyName(String companyName){
        if (companyName != null){
            return iInventoryGroupRepo.findBygCompanyName(companyName);
        }
        return null;
    }

    //add inventory group
    @Transactional
    public void addInventoryGroup(InventoryGroup ig){
        iInventoryGroupRepo.save(ig);
    }

    // updates inventory groups record
    @Transactional
    public void updateInventoryGroups(InventoryGroup ig){
        iInventoryGroupRepo.save(ig);
    }

    // removes inventory groups record
    @Transactional
    public void removeInventoryGroups(Long id){
        iInventoryGroupRepo.deleteById(id);
    }
}
