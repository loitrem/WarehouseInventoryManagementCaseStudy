package org.perscholas.services;

import org.perscholas.dao.IEmployeesRepo;
import org.perscholas.dao.IUserRepo;
import org.perscholas.models.Employees;
import org.perscholas.models.UserType;
import org.perscholas.models.Users;
import org.perscholas.security.AppSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    IUserRepo iUserRepo;
    IEmployeesRepo iEmployeesRepo;

    @Autowired
    public UserService(IUserRepo iUserRepo, IEmployeesRepo iEmployeesRepo) {
        this.iUserRepo = iUserRepo;
        this.iEmployeesRepo = iEmployeesRepo;
    }

    //find all users
    public List<Users> findAllUsers(){ return iUserRepo.findAll(); }

    //find users by id
    public Users findById(Long id){
        if (id != 0){
            return iUserRepo.findByuId(id);
        }
        return null;
    }

    //find users by username
    public Users findByuUsername(String username){
        if (username != null){
            return iUserRepo.findByuUsername(username);
        }
        return null;
    }

    //find users by usertype
    public List<Users> findByuUserType(UserType userType){
        if (userType!= null){
            return iUserRepo.findByuUserType(userType);
        }
        return null;
    }

    //find users by usertype
    public Users findByuEmployee(Employees e){
        if (e!= null){
            return iUserRepo.findByuEmployeeUsername(e);
        }
        return null;
    }

    //register an employee as a user
    public void addUser(Users users, Employees employees){

        // throws exception is employee
        if (iUserRepo.findByuUsername(users.getUUsername())!=null){
            throw new IllegalStateException("Employee already a user");
        }
        // encode the password with BCrypt
        users.setUPassword(AppSecurityConfiguration.getPasswordEncoder().encode(users.getUPassword()));

        iUserRepo.save(users);
        iEmployeesRepo.save(employees);
    }

    //edit users
    @Transactional
    public void updateUsers(Users u){
        iUserRepo.save(u);
    }
}
