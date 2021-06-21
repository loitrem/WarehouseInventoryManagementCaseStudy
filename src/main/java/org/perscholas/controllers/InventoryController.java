package org.perscholas.controllers;

import org.perscholas.models.Departments;
import org.perscholas.models.Employees;
import org.perscholas.models.Inventory;
import org.perscholas.models.InventoryGroup;
import org.perscholas.services.DepartmentService;
import org.perscholas.services.EmployeeService;
import org.perscholas.services.InventoryGroupService;
import org.perscholas.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
@RequestMapping("inventory")
public class InventoryController {

    InventoryService inventoryService;
    InventoryGroupService inventoryGroupService;

    @Autowired
    public InventoryController(InventoryService inventoryService, InventoryGroupService inventoryGroupService) {
        this.inventoryService = inventoryService;
        this.inventoryGroupService = inventoryGroupService;
    }

    @ModelAttribute("inventory")
    public Inventory initInventory(){ return new Inventory(); }

    @GetMapping("/showinventory")
    public String showInventory(Model model){

        List<Inventory> i = inventoryService.findAllInventory();
        model.addAttribute("inventory", i);
        return "inventory";
    }

//    @GetMapping("/userprofile/{eId}")
//    public String userProfile(@ModelAttribute("emp") @Valid Employees employees, BindingResult result, Model model,
//                              @ModelAttribute("dept") @Valid Departments dept, BindingResult result2, Model model2,
//                              @PathVariable("eId") Long id){
//
//        Employees e = employeeService.findById(id);
//        Departments d = e.getEDepartment();
//        model.addAttribute("emp", e);
//        model2.addAttribute("dept", d);
//        return "profile";
//    }
//
//    @PostMapping("/profile/{eId}")
//    public String profile(@ModelAttribute("emp") @Valid Employees employees, BindingResult result, Model model,
//                          @ModelAttribute("dept") @Valid Departments dept, BindingResult result2, Model model2,
//                          @PathVariable("eId") Long id){
//
//        Employees e = employeeService.findById(id);
//        Departments d = e.getEDepartment();
//        model.addAttribute("emp", e);
//        model2.addAttribute("dept", d);
//        return "profile";
//    }
//
    @GetMapping("/inventorysearch")
    public String inventorySearch(Model model, Model model2){

        List<Inventory> i = inventoryService.findAllInventory();
        List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();

        model.addAttribute("inventory", i);
        model2.addAttribute("inventorygroup", ig);
        return "inventorysearch";
    }
}
