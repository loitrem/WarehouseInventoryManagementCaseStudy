package org.perscholas.services;

import org.perscholas.dao.IDepartmentsRepo;
import org.perscholas.models.Departments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    IDepartmentsRepo iDepartmentsRepo;

    @Autowired
    public DepartmentService(IDepartmentsRepo iDepartmentsRepo) {
        this.iDepartmentsRepo = iDepartmentsRepo;
    }

    //find all departments
    public List<Departments> findAllDepartments(){
        return iDepartmentsRepo.findAll();
    }

    //find department by id
    public Optional<Departments> findById(int id){
        if (id!=0){
            return iDepartmentsRepo.findBydId(id);
        }
        return Optional.empty();
    }
}
