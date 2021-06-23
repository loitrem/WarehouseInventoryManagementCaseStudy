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
        Employees employees = employeeService.findById(users.getUEmployeeUsername().getEId());
        List<UserType> userType = userTypeService.findAllUserTypes();

        model.addAttribute("u", users);
        model2.addAttribute("emp", employees);
        model3.addAttribute("usertype", userType);
        return "usersedit";
    }

    //save edited employee
    @PostMapping("/save")
    public String saveUsers(Model model, @RequestParam("uId") Long uId, @RequestParam("usertype") Long userTypeId){

        UserType ut = userTypeService.findByUserTypeId(userTypeId);
        Users updateUser = userService.findById(uId);
        updateUser.setUUserType(ut);
        userService.updateUsers(updateUser);

        List<Users> u = userService.findAllUsers();
        model.addAttribute("users", u);

        return "showusers";
    }

    //displays edit user
    @GetMapping("search")
    public String searchUsers(Model model, Model model2, Model model3){
        List<Users> users = userService.findAllUsers();
        List<Employees> employees = employeeService.findByeUserId();
        List<UserType> userType = userTypeService.findAllUserTypes();
        model.addAttribute("users", users);
        model2.addAttribute("employees", employees);
        model3.addAttribute("usertype", userType);
        return "usersearch";
    }

    //show all users by employee name
    @PostMapping("/userbyemployeename")
    public String userByEmployeeName(Model model, @RequestParam("employee") Long eId){
        Employees e = employeeService.findById(eId);
        Users u = userService.findById(e.getEUserId().getUId());
        model.addAttribute("users", u);

        return "showusers";
    }

    //show all users by user type
    @PostMapping("/userbyusertype")
    public String userByUserType(Model model, @RequestParam("usertype") Long userTypeId){
        UserType ut = userTypeService.findByUserTypeId(userTypeId);
        List<Users> u = userService.findByuUserType(ut);
        model.addAttribute("users", u);

        return "showusers";
    }

    //show all users by username
    @PostMapping("/userbyusername")
    public String userByUsername(Model model, @RequestParam("username") Long uId){
        Users u = userService.findById(uId);
        model.addAttribute("users", u);

        return "showusers";
    }
}
