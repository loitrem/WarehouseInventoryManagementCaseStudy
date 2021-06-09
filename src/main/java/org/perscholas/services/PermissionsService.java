package org.perscholas.services;

import org.perscholas.dao.IPermissionsRepo;
import org.perscholas.models.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionsService {

    IPermissionsRepo iPermissionsRepo;

    @Autowired
    public PermissionsService(IPermissionsRepo iPermissionsRepo) {
        this.iPermissionsRepo = iPermissionsRepo;
    }

    //find all permissions
    public List<Permissions> findAllPermissions(){
        return  iPermissionsRepo.findAll();
    }

    //find permissions by id
    public Optional<Permissions> findBypId(int id){
        if (id != 0){
            return iPermissionsRepo.findBypId(id);
        }
        return Optional.empty();
    }
}
