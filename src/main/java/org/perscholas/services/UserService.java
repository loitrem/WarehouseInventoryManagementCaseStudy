package org.perscholas.services;

import org.perscholas.dao.IUserRepo;
import org.perscholas.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    IUserRepo iUserRepo;

    @Autowired
    public UserService(IUserRepo iUserRepo) {
        this.iUserRepo = iUserRepo;
    }

    //find all users
    public List<Users> findAllUsers(){ return iUserRepo.findAll(); }

    //find users by id
    public Optional<Users> findById(int id){
        if (id != 0){
            return iUserRepo.findByuId(id);
        }
        return Optional.empty();
    }

    //find users by username
    public Optional<Users> findByuUsername(String username){
        if (username != null){
            return iUserRepo.findByuUsername(username);
        }
        return Optional.empty();
    }

    //find users by usertype
    public Optional<Users> findByuUserType(int userType){
        if (userType != 0){
            return iUserRepo.findByuUserType(userType);
        }
        return Optional.empty();
    }
}
