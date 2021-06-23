package org.perscholas.controllers;

import org.perscholas.models.Employees;
import org.perscholas.models.UserType;
import org.perscholas.models.Users;
import org.perscholas.services.EmployeeService;
import org.perscholas.services.UserService;
import org.perscholas.services.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("users")
public class UsersController {

    UserService userService;
    EmployeeService employeeService;
    UserTypeService userTypeService;

    @Autowired
    public UsersController(UserService userService, EmployeeService employeeService, UserTypeService userTypeService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.userTypeService = userTypeService;
    }

    @ModelAttribute("users")
    public Users initUsers(){ return new Users(); }

    @GetMapping("/home")
    public String home(){
        return "home";
    }


}
