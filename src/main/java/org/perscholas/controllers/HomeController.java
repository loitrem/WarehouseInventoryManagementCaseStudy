package org.perscholas.controllers;

import org.perscholas.models.Employees;
import org.perscholas.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    EmployeeService employeeService;

    @Autowired
    public HomeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping({"/","index"})
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String showEmployees(@ModelAttribute("employees") @Valid Employees employees, BindingResult result, Model model){

        List<Employees> e = employeeService.findAllEmployees();
        model.addAttribute("employees", e);
        return "register";
    }
}
