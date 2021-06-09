package org.perscholas.services;

import org.perscholas.dao.IStatusRepo;
import org.perscholas.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Status> findBysId(String name){
        if (name!=null){
            return iStatusRepo.findBysName(name);
        }
        return Optional.empty();
    }
}
