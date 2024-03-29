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
import java.util.ArrayList;
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
        List<Employees> emp = employeeService.findAllUserIdIfNotNull();
        model.addAttribute("employees", emp);
        return "showusers";
    }

    //displays edit user
    @GetMapping("/edit/{uId}")
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

        List<Employees> emp = employeeService.findAllUserIdIfNotNull();
        model.addAttribute("employees", emp);

        return "showusers";
    }

    //displays edit user
    @GetMapping("search")
    public String searchUsers(Model model, Model model2, Model model3){
        List<Users> users = userService.findAllUsers();
        List<Employees> employees = employeeService.findAllUserIdIfNotNull();
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

        model.addAttribute("employees", e);

        return "showusers";
    }

    //show all users by user type
    @PostMapping("/userbyusertype")
    public String userByUserType(Model model,Model model2, @RequestParam("usertype") Long userTypeId){
        UserType ut = userTypeService.findByUserTypeId(userTypeId);
        List<Users> u = userService.findByuUserType(ut);
        List<Employees> e = new ArrayList<>();
        for (Users user : u){
            if (employeeService.findByeUserId(user)!=null){
                e.add(employeeService.findByeUserId(user));
            }
        }
        model.addAttribute("users", u);
        model.addAttribute("employees", e);

        return "showusers";
    }

    //show all users by username
    @PostMapping("/userbyusername")
    public String userByUsername(Model model, Model model2, @RequestParam("username") Long uId){
        Users u = userService.findById(uId);
        Employees e = employeeService.findByeUserId(u);
        model.addAttribute("users", u);
        model2.addAttribute("employees", e);

        return "showusers";
    }

    //displays remove user
    @GetMapping("/remove/{uId}")
    public String removeUsers(@PathVariable("uId") Long uId, Model model){

        // get user by id, get employee by user obj, set user obj inside employee to null, update employee, then remove user
        Users u = userService.findById(uId);
        Employees e = employeeService.findByeUserId(u);
        e.setEUserId(null);
        employeeService.updateEmployees(e);
        userService.removeUsers(u);

        List<Employees> emp = employeeService.findAllUserIdIfNotNull();
        model.addAttribute("employees", emp);
        return "showusers";
    }
}
