package org.perscholas.services;

import org.perscholas.dao.IEmployeesRepo;
import org.perscholas.models.Departments;
import org.perscholas.models.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    IEmployeesRepo iEmployeesRepo;
    DepartmentService departmentService;

    @Autowired
    public EmployeeService(IEmployeesRepo iEmployeesRepo) {
        this.iEmployeesRepo = iEmployeesRepo;
    }

    //find all employees
    public List<Employees> findAllEmployees(){
        return  iEmployeesRepo.findAll();
    }

    //find employees by id
    public Employees findById(Long id){
        if (id != 0){
            return iEmployeesRepo.findByeId(id);
        }
        return null;
    }

    //find employees by job title
    public List<Employees> findByeJobTitle(String title){
        if (title != null){
            return iEmployeesRepo.findByeJobTitle(title);
        }
        return null;
    }

    //find employees by department
    public List<Employees> findByDept(Departments department){
        if (department != null) {
            return iEmployeesRepo.findByeDepartment(department);
        }

       return null;
    }

    //edit employees
    @Transactional
    public void updateEmployees(Employees e){
        iEmployeesRepo.save(e);
    }

    //add employees
    @Transactional
    public void addEmployees(Employees e){
        iEmployeesRepo.save(e);
    }
}
