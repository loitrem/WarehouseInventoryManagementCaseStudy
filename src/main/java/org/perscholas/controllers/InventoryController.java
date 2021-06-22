package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.models.*;
import org.perscholas.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("inventory")
public class InventoryController {

    InventoryService inventoryService;
    InventoryGroupService inventoryGroupService;
    EmployeeService employeeService;
    StatusService statusService;

    @Autowired
    public InventoryController(InventoryService inventoryService, InventoryGroupService inventoryGroupService, EmployeeService employeeService, StatusService statusService) {
        this.inventoryService = inventoryService;
        this.inventoryGroupService = inventoryGroupService;
        this.employeeService = employeeService;
        this.statusService = statusService;
    }

    @ModelAttribute("inventory")
    public Inventory initInventory(){ return new Inventory(); }

    @GetMapping("/showinventory")
    public String showInventory(Model model){

        List<Inventory> i = inventoryService.findAllInventory();
        model.addAttribute("inventory", i);
        return "showinventory";
    }

    @GetMapping("/inventorysearch")
    public String inventorySearch(Model model, Model model2){

        List<Inventory> i = inventoryService.findAllInventory();
        List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();

        model.addAttribute("inventory", i);
        model2.addAttribute("inventorygroup", ig);
        return "inventorysearch";
    }

    @GetMapping("/{iId}")
    public String inventoryDisplay(Model model, @PathVariable("iId") Long id){

        Inventory i = inventoryService.findById(id);
        model.addAttribute("inv", i);

        return "inventory";
    }

    @PostMapping("/showinventoryitemnumber")
    public String findByItemNumber(Model model, @RequestParam("number") String itemNumber){

        List<Inventory> i = inventoryService.findByItemNumber(itemNumber);
        model.addAttribute("inventory", i);
        return "showinventory";
    }

    @PostMapping("/showinventorydesc")
    public String findByDescription(Model model, @RequestParam("desc") String desc){
        List<Inventory> i = inventoryService.findByDescription(desc);
        model.addAttribute("inventory", i);
        return "showinventory";
    }

    @PostMapping("/showinventorystatus")
    public String findByStatus(Model model, @RequestParam("status") String status){
        Status s = statusService.findBysStatus(status);
        List<Inventory> i = inventoryService.findByStatus(s);
        model.addAttribute("inventory", i);
        return "showinventory";
    }

    @PostMapping("/showinventorygroup")
    public String findByGroup(Model model, @RequestParam("group") Long id){
        InventoryGroup ig = inventoryGroupService.findById(id);
        List<Inventory> i = inventoryService.findByInventoryGroup(ig);
        model.addAttribute("inventory", i);
        return "showinventory";
    }

    @GetMapping("/inventoryedit/{iId}")
    public String showEditInventory(@PathVariable("iId") Long id, Model model, Model model2, Model model3, Model model4){


        Inventory i = inventoryService.findById(id);
        List<Employees> e = employeeService.findAllEmployees();
        List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();
        List<Status> s = statusService.findAllStatus();
        model.addAttribute("in", i);
        model2.addAttribute("emp", e);
        model3.addAttribute("invgroup", ig);
        model4.addAttribute("status", s);

        return "inventoryedit";
    }

    //edit inventory
    @PostMapping("/save")
    public String editInventory(@ModelAttribute("in") @Valid Inventory inventory, BindingResult result, Model model,
                                @RequestParam("movedby") Long eId, @RequestParam("invgroup") Long gId, @RequestParam("status") Long sId){
        Employees e = employeeService.findById(eId);
        InventoryGroup ig = inventoryGroupService.findById(gId);
        Status s = statusService.findBysId(sId);

        inventory.setIMovedBy(e);
        inventory.setIInventoryGroup(ig);
        inventory.setIStatus(s);

        inventoryService.updateInventory(inventory);

        return"saved";
    }

}
