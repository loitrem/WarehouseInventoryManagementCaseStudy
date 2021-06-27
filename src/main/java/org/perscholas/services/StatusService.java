package org.perscholas.services;

import org.perscholas.dao.IStatusRepo;
import org.perscholas.models.Employees;
import org.perscholas.models.Inventory;
import org.perscholas.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatusService {

    IStatusRepo iStatusRepo;

    @Autowired
    public StatusService(IStatusRepo iStatusRepo) {
        this.iStatusRepo = iStatusRepo;
    }

    //find all status
    public List<Status> findAllStatus(){
        return  iStatusRepo.findAll();
    }

    //find status by id
    public Status findBysId(Long id){
        if (id!=0){
            return iStatusRepo.findBysId(id);
        }
        return null;
    }

    //find status by status
    public Status findBysStatus(String status){
        if (status!=null){
            return iStatusRepo.findBysStatus(status);
        }
        return null;
    }

    //add status
    @Transactional
    public void addStatus(Status s){
        iStatusRepo.save(s);
    }

    //Remove status
    @Transactional
    public void removeStatus(Status s){

        if (iStatusRepo.findBysStatus(s.getSStatus())!=null) {
            iStatusRepo.delete(s);
        }
    }

}
