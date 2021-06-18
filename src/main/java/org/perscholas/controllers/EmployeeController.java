package org.perscholas.controllers;

import org.perscholas.models.Departments;
import org.perscholas.models.Employees;
import org.perscholas.services.DepartmentService;
import org.perscholas.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String showEmployees(@ModelAttribute("employees") @Valid Employees employees, BindingResult result, Model model){

        List<Employees> e = employeeService.findAllEmployees();
        model.addAttribute("employees", e);
        return "employees";
    }

    @GetMapping("/userprofile/{eId}")
    public String userProfile(@ModelAttribute("emp") @Valid Employees employees, BindingResult result, Model model,
                          @ModelAttribute("dept") @Valid Departments dept, BindingResult result2, Model model2,
                          @PathVariable("eId") Long id){

        Employees e = employeeService.findById(id);
        Departments d = e.getEDepartment();
        model.addAttribute("emp", e);
        model2.addAttribute("dept", d);
        return "profile";
    }

    @PostMapping("/profile/{eId}")
    public String profile(@ModelAttribute("emp") @Valid Employees employees, BindingResult result, Model model,
                          @ModelAttribute("dept") @Valid Departments dept, BindingResult result2, Model model2,
                          @PathVariable("eId") Long id){

        Employees e = employeeService.findById(id);
        Departments d = e.getEDepartment();
        model.addAttribute("emp", e);
        model2.addAttribute("dept", d);
        return "profile";
    }

    @GetMapping("/employeesearch")
            public String employeeSearch(@ModelAttribute("employees") @Valid Employees employees, BindingResult result, Model model,
                                         @ModelAttribute("dept") @Valid Departments dept, BindingResult result2, Model model2){

            List<Employees> e = employeeService.findAllEmployees();
            List<Departments> d = departmentService.findAllDepartments();
            model.addAttribute("employees", e);
            model2.addAttribute("departments", d);
        return "employeesearch";
}
}
