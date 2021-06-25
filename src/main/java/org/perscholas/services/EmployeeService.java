package org.perscholas.services;

import org.perscholas.dao.IEmployeesRepo;
import org.perscholas.models.Departments;
import org.perscholas.models.Employees;
import org.perscholas.models.Inventory;
import org.perscholas.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    IEmployeesRepo iEmployeesRepo;
    DepartmentService departmentService;
    InventoryService inventoryService;

    @Autowired
    public EmployeeService(IEmployeesRepo iEmployeesRepo, DepartmentService departmentService, InventoryService inventoryService) {
        this.iEmployeesRepo = iEmployeesRepo;
        this.departmentService = departmentService;
        this.inventoryService = inventoryService;
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

    //Remove employees
    @Transactional
    public void removeEmployees(Employees e){

        Inventory i = inventoryService.findByIMovedBy(e);
        i.setIMovedBy(null);
        inventoryService.updateInventory(i);
        iEmployeesRepo.delete(e);
    }

    //find all employees by UserId if exists
    public List<Employees> findAllUserIdIfNotNull(){
        return  iEmployeesRepo.findAllUserIdIfNotNull();
    }

    //find employees by UserId
    public Employees findByeUserId(Users u){
        return  iEmployeesRepo.findByeUserId(u);
    }
}
