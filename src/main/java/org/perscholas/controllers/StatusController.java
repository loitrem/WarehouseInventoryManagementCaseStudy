package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.models.Departments;
import org.perscholas.models.Employees;
import org.perscholas.models.Status;
import org.perscholas.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("status")
public class StatusController {

    StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    //model attribute for status
    @ModelAttribute("status")
    public Status initEmployees(){ return new Status(); }

    //displays a list of all employees
    @GetMapping("/show")
    public String showEmployees(Model model){

        List<Status> s = statusService.findAllStatus();
        model.addAttribute("status", s);
        return "showstatus";
    }

    //show add new status
    @GetMapping("/add")
    public String showAddStatus(Model model){

        Status s = new Status();
        model.addAttribute("status", s);
        return"statusadd";
    }

    //save a new employee record
    @PostMapping("/add")
    public String addStatus(@ModelAttribute("status") @Valid Status status, BindingResult result, Model model){

        if (statusService.findBysStatus(status.getSStatus())==null) {
            statusService.addStatus(status);

            List<Status> s = statusService.findAllStatus();
            model.addAttribute("status", s);

            return"showstatus";
        }

        Status s = new Status();
        model.addAttribute("status", s);
        return"statusadd";
    }

    //remove a status record
    @GetMapping("/remove/{sId}")
    public String removeStatus(@PathVariable("sId") Long id, Model model){

        Status s = statusService.findBysId(id);
        statusService.removeStatus(s);

        List<Status> status = statusService.findAllStatus();
        model.addAttribute("status", status);

        return"showstatus";
    }
}
