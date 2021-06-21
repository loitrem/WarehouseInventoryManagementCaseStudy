package org.perscholas.controllers;

import org.perscholas.dao.IInventoryGroupRepo;
import org.perscholas.models.Inventory;
import org.perscholas.models.InventoryGroup;
import org.perscholas.services.InventoryGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/showinventorygroups")
    public String showInventoryGroups(Model model){

        List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();
        model.addAttribute("inventorygroups", ig);
        return "showinventorygroups";
    }
}
