package org.perscholas.controllers;

import org.perscholas.models.Departments;
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

    //model attribute for employees
    @ModelAttribute("users")
    public Users initUsers(){ return new Users(); }

    //displays home in users area (logged in)
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    //displays all users
    @GetMapping("show")
    public String showUsers(Model model){
        List<Users> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "showusers";
    }

    //displays edit user
    @GetMapping("edit/{uId}")
    public String editUsers(@PathVariable("uId") Long uId, Model model, Model model2, Model model3){
        Users users = userService.findById(uId);
        Employees employees = employeeService.findById(users.getUEmployee_Username().getEId());
        UserType userType = userTypeService.findByUserTypeId(users.getUUserType().getUserTypeId());

        model.addAttribute("users", users);
        model.addAttribute("emp", employees);
        model.addAttribute("usertype", userType);
        return "showusers";
    }

    //save edited employee
    @PostMapping("/save")
    public String saveUsers(@ModelAttribute("users") @Valid Users users, BindingResult result, Model model,
                            @RequestParam("uId") Long id, @RequestParam("employee") Long eId, @RequestParam("usertype") Long userTypeId){

        Employees e = employeeService.findById(eId);
        users.setUEmployee_Username(e);
        UserType ut = userTypeService.findByUserTypeId(userTypeId);
        users.setUUserType(ut);
        userService.updateUsers(users);

        List<Users> u = userService.findAllUsers();
        model.addAttribute("users", u);

        return "showusers";
    }
}
