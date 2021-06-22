package org.perscholas.controllers;

import org.perscholas.dao.IInventoryGroupRepo;
import org.perscholas.models.Employees;
import org.perscholas.models.Inventory;
import org.perscholas.models.InventoryGroup;
import org.perscholas.models.Status;
import org.perscholas.services.InventoryGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("inventorygroups")
public class InventoryGroupController {

    InventoryGroupService inventoryGroupService;

    @Autowired
    public InventoryGroupController(InventoryGroupService inventoryGroupService) {
        this.inventoryGroupService = inventoryGroupService;
    }

    @ModelAttribute("inventorygroups")
    public InventoryGroup initInventoryGroup(){ return new InventoryGroup(); }

    @GetMapping("/show")
    public String showInventoryGroups(Model model){

        List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();
        model.addAttribute("inventorygroups", ig);
        return "showinventorygroups";
    }

    @GetMapping("/search")
    public String inventoryGroupSearch(Model model){

        List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();

        model.addAttribute("invgroup", ig);
        return "inventorygroupsearch";
    }

    @GetMapping("/{gId}")
    public String inventoryGroupsDisplay(Model model, @PathVariable("gId") Long id){

        InventoryGroup ig = inventoryGroupService.findById(id);
        model.addAttribute("ig", ig);

        return "inventorygroups";
    }

    @PostMapping("/showinventorygroupcompanyname")
    public String findByCompanyName(Model model, @RequestParam("company") String name) {
        List<InventoryGroup> ig = inventoryGroupService.findByCompanyName(name);
        model.addAttribute("inventorygroups", ig);
        return "showinventorygroups";
    }

    @PostMapping("/showinventorygroupcontactname")
    public String findByContactName(Model model, @RequestParam("contact") Long id) {
        InventoryGroup ig = inventoryGroupService.findById(id);
        model.addAttribute("inventorygroups", ig);
        return "showinventorygroups";
    }

    //show add a new inventory group record page
    @GetMapping("/add")
    public String showAddInventory(Model model){

        InventoryGroup ig = new InventoryGroup();

        model.addAttribute("ig", ig);

        return"inventorygroupadd";
    }

    //save a new inventory group record
    @PostMapping("/add")
    public String addInventory(@ModelAttribute("ig") @Valid InventoryGroup inventoryGroup, BindingResult result){

        inventoryGroupService.addInventoryGroup(inventoryGroup);

        return"inventorygroupadd";
    }

    //displays the edit page for inventory group record
    @GetMapping("/edit/{gId}")
    public String inventoryGroupsEdit(Model model, @PathVariable("gId") Long id){

        InventoryGroup ig = inventoryGroupService.findById(id);
        model.addAttribute("ig", ig);

        return "inventorygroupsedit";
    }

    //saves the changes to inventory group record
    @PostMapping("/save")
    public String editInventoryGroups(@ModelAttribute("ig") @Valid InventoryGroup inventoryGroup, BindingResult result, Model model){

        inventoryGroupService.updateInventoryGroups(inventoryGroup);

        List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();
        model.addAttribute("inventorygroups", ig);

        return "showinventorygroups";
    }

    //remove an inventory record
    @GetMapping("/remove/{gId}")
    public String removeInventoryGroups(@PathVariable("gId") Long id, Model model){
        inventoryGroupService.removeInventoryGroups(id);

        List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();
        model.addAttribute("inventorygroups", ig);

        return"showinventory";
    }
}
