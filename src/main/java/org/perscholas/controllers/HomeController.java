package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class HomeController {

    EmployeeService employeeService;
    UserTypeService userTypeService;
    UserService userService;

    @Autowired
    public HomeController(EmployeeService employeeService, UserTypeService userTypeService, UserService userService) {
        this.employeeService = employeeService;
        this.userTypeService = userTypeService;
        this.userService = userService;
    }

    //display index page
    @GetMapping({"/","index"})
    public String index(){
        return "index";
    }

    //display login page
    @GetMapping({"/login"})
    public String login(){
        return "login";
    }

    //displays register user page
    @GetMapping("/register")
    public String showEmployees(@ModelAttribute("employees") @Valid Employees employees, BindingResult result, Model model, Model model2){

        List<Employees> e = employeeService.findAllEmployees();
        model.addAttribute("employees", e);
        Users u = new Users();
        model2.addAttribute("users", u);
        return "register";
    }

    // register employee as a user
    @PostMapping("/register")
    public String authenticate(@ModelAttribute("users") @Valid Users users, BindingResult result, Model model,
                               @RequestParam("id") Long eId,  @RequestParam("password2") String pass2){

        if (!users.getUPassword().equals(pass2)){
            log.warn(users.getUPassword());
            log.warn(pass2);
            throw new IllegalStateException("Passwords Do not Match");
        }

        // sets user object into employee object, sets usertype object of user into user object, and sends user obj and employee obj to user service to save
        Employees e = employeeService.findById(eId);
        e.setEUserId(users);
        UserType ut = userTypeService.findByUserTypeName("ROLE_USER");
        users.setUUserType(ut);

        userService.addUser(users, e);

        return "/login";
    }
}
