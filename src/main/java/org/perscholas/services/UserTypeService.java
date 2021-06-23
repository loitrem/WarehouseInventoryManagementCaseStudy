package org.perscholas.services;

import org.perscholas.dao.IUserTypeRepo;
import org.perscholas.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService {

    IUserTypeRepo iUserTypeRepo;

    @Autowired
    public UserTypeService(IUserTypeRepo iUserTypeRepo) {
        this.iUserTypeRepo = iUserTypeRepo;
    }

    //find all user types
    public List<UserType> findAllUserTypes(){ return iUserTypeRepo.findAll(); }

    //find user types by id
    public UserType findByUserTypeId(Long id){
        if (id != 0){
            return iUserTypeRepo.findByuserTypeId(id);
        }
        return null;
    }

    //find user types by userTypeName
    public UserType findByUserTypeName(String name){
        if (name!=null){
            return iUserTypeRepo.findByuserTypeName(name);
        }
        return null;
    }
}
