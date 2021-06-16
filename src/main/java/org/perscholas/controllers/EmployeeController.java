package org.perscholas.controllers;

import org.perscholas.models.Employees;
import org.perscholas.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("employees")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ModelAttribute("employees")
    public Employees initEmployees(){ return new Employees(); }

    @GetMapping("/showemployees")
    public String showEmployees(@ModelAttribute("employees") @Valid Employees employees, BindingResult result, Model model){

        List<Employees> e = employeeService.findAllEmployees();
        model.addAttribute("employees", e);
        return "employees";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }
}
