package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.models.Departments;
import org.perscholas.models.Employees;
import org.perscholas.services.DateService;
import org.perscholas.services.DepartmentService;
import org.perscholas.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("employees")
public class EmployeeController {

    EmployeeService employeeService;
    DepartmentService departmentService;
    DateService dateService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, DateService dateService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.dateService = dateService;
    }

    @ModelAttribute("employees")
    public Employees initEmployees(){ return new Employees(); }

    @GetMapping("/show")
    public String showEmployees(Model model){

        List<Employees> e = employeeService.findAllEmployees();
        model.addAttribute("employees", e);
        return "showemployees";
    }

    @GetMapping("/userprofile/{eId}")
    public String userProfile(Model model, Model model2, @PathVariable("eId") Long id){

        Employees e = employeeService.findById(id);
        Departments d = e.getEDepartment();
        model.addAttribute("e", e);
        model2.addAttribute("dept", d);
        return "profile";
    }

    @GetMapping("/profile/{eId}")
    public String profile(Model model, Model model2, @PathVariable("eId") Long id){

        Employees e = employeeService.findById(id);
        Departments d = e.getEDepartment();
        model.addAttribute("e", e);
        model2.addAttribute("dept", d);
        return "profile";
    }

    @GetMapping("/search")
            public String employeeSearch(Model model, Model model2){

            List<Employees> e = employeeService.findAllEmployees();
            List<Departments> d = departmentService.findAllDepartments();
            model.addAttribute("employees", e);
            model2.addAttribute("departments", d);
        return "employeesearch";
    }

    @PostMapping("/employeebyname")
    public String employeeByName(Model model, @RequestParam("id") Long id){

        Employees e = employeeService.findById(id);
        model.addAttribute("e", e);
        return "profile";
    }

    @PostMapping("/employeebydept")
    public String employeeByDept(Model model, @RequestParam("dept") Long id){

        Departments d = departmentService.findById(id);
        List<Employees> e = employeeService.findByDept(d);
        model.addAttribute("employees", e);

        return "showemployees";
    }

    @PostMapping("/employeebytitle")
    public String employeeByJobTitle(Model model, @RequestParam("title") String title){
        List<Employees> e = employeeService.findByeJobTitle(title);
        model.addAttribute("employees", e);

        return "showemployees";
    }

    @GetMapping("/edit/{eId}")
    public String employeesEdit(Model model, Model model2, @PathVariable("eId") Long id){

        Employees e = employeeService.findById(id);
        List<Departments> d = departmentService.findAllDepartments();
        model.addAttribute("emp", e);
        log.warn("object is " + e.toString());
        model2.addAttribute("dept", d);
        return "employeesedit";
    }

    @PostMapping("/save")
    public String editEmployees(@ModelAttribute("emp") @Valid Employees employees, BindingResult result, Model model, @RequestParam("deptId") Long id){
        log.warn("object in POST " + employees.toString());
        Departments d = departmentService.findById(id);
        employees.setEDepartment(d);
        employeeService.updateEmployees(employees);

        List<Employees> e = employeeService.findAllEmployees();
        model.addAttribute("employees", e);

        return"showemployees";
    }

    //show add new employee record page
    @GetMapping("/add")
    public String showAddEmployees(Model model, Model model2){
        model.addAttribute("dept", departmentService.findAllDepartments());
        Employees emp = new Employees();
        model2.addAttribute("employees", emp);
        return"employeesadd";
    }

    //save a new employee record
    @PostMapping("/add")
    public String addEmployees(@ModelAttribute("employees") @Valid Employees employees, BindingResult result, Model model, @RequestParam("deptId") Long id){

        Departments d = departmentService.findById(id);
        employees.setEDepartment(d);

        employeeService.addEmployees(employees);

        List<Employees> emp = employeeService.findAllEmployees();
        model.addAttribute("employees", emp);

        return"showemployees";
    }

    //remove an employee record
    @GetMapping("/remove/{eId}")
    public String removeEmployees(@PathVariable("eId") Long id, Model model){

        Employees emp = employeeService.findById(id);
        employeeService.removeEmployees(emp);

        List<Employees> e = employeeService.findAllEmployees();
        model.addAttribute("employees", e);

        return"showemployees";
    }
}
