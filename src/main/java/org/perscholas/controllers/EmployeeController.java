package org.perscholas.controllers;

import org.perscholas.models.Departments;
import org.perscholas.models.Employees;
import org.perscholas.services.DepartmentService;
import org.perscholas.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("employees")
public class EmployeeController {

    EmployeeService employeeService;
    DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @ModelAttribute("employees")
    public Employees initEmployees(){ return new Employees(); }

    @GetMapping("/showemployees")
    public String showEmployees(Model model){

        List<Employees> e = employeeService.findAllEmployees();
        model.addAttribute("employees", e);
        return "employees";
    }

    @GetMapping("/userprofile/{eId}")
    public String userProfile(Model model, Model model2, @PathVariable("eId") Long id){

        Employees e = employeeService.findById(id);
        Departments d = e.getEDepartment();
        model.addAttribute("emp", e);
        model2.addAttribute("dept", d);
        return "profile";
    }

    @PostMapping("/profile/{eId}")
    public String profile(Model model, Model model2, @PathVariable("eId") Long id){

        Employees e = employeeService.findById(id);
        Departments d = e.getEDepartment();
        model.addAttribute("emp", e);
        model2.addAttribute("dept", d);
        return "profile";
    }

    @GetMapping("/employeesearch")
            public String employeeSearch(Model model, Model model2){

            List<Employees> e = employeeService.findAllEmployees();
            List<Departments> d = departmentService.findAllDepartments();
            model.addAttribute("employees", e);
            model2.addAttribute("departments", d);
        return "employeesearch";
    }

    @PostMapping("/employeebyname")
    public String employeeByName(Model model, Model model2, @RequestParam("id") Long id){

        Employees e = employeeService.findById(id);
        Departments d = e.getEDepartment();
        model.addAttribute("emp", e);
        model2.addAttribute("dept", d);
        return "profile";
    }

    @PostMapping("/employeebydept")
    public String employeeByDept(Model model, @RequestParam("dept") Long id){

        Departments d = departmentService.findById(id);
        List<Employees> e = employeeService.findByDept(d);
        model.addAttribute("employees", e);

        return "employees";
    }

    @PostMapping("/employeebytitle")
    public String employeeByJobTitle(Model model, @RequestParam("title") String title){
        List<Employees> e = employeeService.findByeJobTitle(title);
        model.addAttribute("employees", e);

        return "employees";
    }
}
