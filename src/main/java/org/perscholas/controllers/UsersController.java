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

    // register employee as a user
    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute("users") @Valid Users users, BindingResult result, Model model, @RequestParam("Id") Long eId){

        // sets user object into employee object, sets usertype object of user into user object, and sends user obj and employee obj to user service to save
        Employees e = employeeService.findById(eId);
        e.setEUser_Id(users);
        UserType ut = userTypeService.findByUserTypeName("ROLE_USER");
        users.setUUserType(ut);

        userService.addUser(users, e);

        return "home";
    }

}
