package org.perscholas.services;

import org.perscholas.dao.IEmployeesRepo;
import org.perscholas.models.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    IEmployeesRepo iEmployeesRepo;

    @Autowired
    public EmployeeService(IEmployeesRepo iEmployeesRepo) {
        this.iEmployeesRepo = iEmployeesRepo;
    }

    //find all employees
    public List<Employees> findAllEmployees(){
        return  iEmployeesRepo.findAll();
    }

    //find employees by id
    public Optional<Employees> findById(int id){
        if (id != 0){
            return iEmployeesRepo.findByeId(id);
        }
        return Optional.empty();
    }

    //find employees by job title
    public Optional<Employees> findByeJobTitle(String title){
        if (title != null){
            return iEmployeesRepo.findByeJobTitle(title);
        }
        return Optional.empty();
    }
}
