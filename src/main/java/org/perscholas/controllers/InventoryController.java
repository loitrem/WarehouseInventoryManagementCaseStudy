package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.models.Employees;
import org.perscholas.models.Inventory;
import org.perscholas.models.InventoryGroup;
import org.perscholas.models.Status;
import org.perscholas.services.EmployeeService;
import org.perscholas.services.InventoryGroupService;
import org.perscholas.services.InventoryService;
import org.perscholas.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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

    //sets model attribute for inventory
    @ModelAttribute("inventory")
    public Inventory initInventory(){ return new Inventory(); }

    //shows all inventory
    @GetMapping("/show")
    public String showInventory(Model model){

        List<Inventory> i = inventoryService.findAllInventory();

        model.addAttribute("inventory", i);

        return "showinventory";
    }

    //displays inventory search page
    @GetMapping("/search")
    public String inventorySearch(Model model, Model model2){

        List<Inventory> i = inventoryService.findAllInventory();
        List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();

        model.addAttribute("inventory", i);
        model2.addAttribute("inventorygroup", ig);

        return "inventorysearch";
    }

    //displays all information for 1 inventory record
    @GetMapping("/{iId}")
    public String inventoryDisplay(Model model, @PathVariable("iId") Long id){

        Inventory i = inventoryService.findById(id);
        if (i.getIMovedBy()==null){
            Employees e = new Employees();
            e.setEDob(Date.from((LocalDate.parse("2021-01-01")).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            e.setEHireDate(Date.from((LocalDate.parse("2021-01-01")).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            e.setEFirstName("Record");
            e.setELastName("Removed");
            i.setIMovedBy(e);
        }

        if (i.getIInventoryGroup()==null){
            InventoryGroup ig = new InventoryGroup();
            ig.setGContractEndDate(Date.from((LocalDate.parse("2021-01-01")).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            ig.setGContractStartDate(Date.from((LocalDate.parse("2021-01-01")).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            ig.setGCompanyName("Record Removed");

            i.setIInventoryGroup(ig);
        }
        model.addAttribute("inv", i);

        return "inventory";
    }

    //shows all inventory by item number
    @PostMapping("/showinventoryitemnumber")
    public String findByItemNumber(Model model,Model model2, Model model3, @RequestParam("number") String itemNumber){

        if (!itemNumber.matches("^[A-Za-z0-9 ]+$")){

            List<Inventory> i = inventoryService.findAllInventory();
            List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();

            model.addAttribute("itemnumerror", "You may only use upper/lower case letters, or numbers in your search");
            model2.addAttribute("inventory", i);
            model3.addAttribute("inventorygroup", ig);

            return "inventorysearch";
        }

        List<Inventory> i = inventoryService.findByItemNumber(itemNumber);
        model.addAttribute("inventory", i);
        return "showinventory";
    }

    //shows all inventory by description
    @PostMapping("/showinventorydesc")
    public String findByDescription(Model model,Model model2, Model model3, @RequestParam("desc") String desc){

        if (!desc.matches("^[A-Za-z ]+$")){

            List<Inventory> i = inventoryService.findAllInventory();
            List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();

            model.addAttribute("descerror", "You may only use upper/lower case letters in your search");
            model2.addAttribute("inventory", i);
            model3.addAttribute("inventorygroup", ig);

            return "inventorysearch";
        }

        List<Inventory> i = inventoryService.findByDescription(desc);
        model.addAttribute("inventory", i);

        return "showinventory";
    }

    //shows all inventory by status
    @PostMapping("/showinventorystatus")
    public String findByStatus(Model model, @RequestParam("status") String status){
        Status s = statusService.findBysStatus(status);
        List<Inventory> i = inventoryService.findByStatus(s);
        model.addAttribute("inventory", i);
        return "showinventory";
    }

    //shows all inventory by inventory group
    @PostMapping("/showinventorygroup")
    public String findByGroup(Model model, @RequestParam("group") Long id){
        InventoryGroup ig = inventoryGroupService.findById(id);
        List<Inventory> i = inventoryService.findByInventoryGroup(ig);
        model.addAttribute("inventory", i);
        return "showinventory";
    }

    //shows add new inventory page
    @GetMapping("/add")
    public String showAddInventory(Model model, Model model2, Model model3, Model model4){
        Inventory inventory = new Inventory();
        List<Employees> e = employeeService.findAllEmployees();
        List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();
        List<Status> s = statusService.findAllStatus();

        model.addAttribute("inventory", inventory);
        model2.addAttribute("emp", e);
        model3.addAttribute("invgroup", ig);
        model4.addAttribute("status", s);

        return "inventoryadd";
    }

    //save a new inventory record
    @PostMapping("/add")
    public String addInventory(@ModelAttribute("inventory") @Valid Inventory inventory, BindingResult result, Model model,
                               @RequestParam("movedby") Long eId, @RequestParam("invgroup") Long gId, @RequestParam("status") Long sId){

        Employees e = employeeService.findById(eId);
        InventoryGroup ig = inventoryGroupService.findById(gId);
        Status s = statusService.findBysId(sId);
        inventory.setIMovedBy(e);
        inventory.setIInventoryGroup(ig);
        inventory.setIStatus(s);

        inventoryService.addinventory(inventory);

        List<Inventory> i = inventoryService.findAllInventory();
        model.addAttribute("inventory", i);

        return"showinventory";
    }

    //shows the edit inventory page
    @GetMapping("/edit/{iId}")
    public String showEditInventory(@PathVariable("iId") Long id, Model model, Model model2, Model model3, Model model4){

        Inventory i = inventoryService.findById(id);
        List<Employees> e = employeeService.findAllEmployees();
        List<InventoryGroup> ig = inventoryGroupService.findAllInventoryGroup();
        List<Status> s = statusService.findAllStatus();
        model.addAttribute("inv", i);
        model2.addAttribute("emp", e);
        model3.addAttribute("invgroup", ig);
        model4.addAttribute("status", s);

        return "inventoryedit";
    }

    //saves the changes to inventory
    @PostMapping("/save")
    public String editInventory(@ModelAttribute("inv") @Valid Inventory inventory, BindingResult result, Model model,
                                @RequestParam("movedby") Long eId, @RequestParam("invgroup") Long gId, @RequestParam("status") Long sId){
        Employees e = employeeService.findById(eId);
        InventoryGroup ig = inventoryGroupService.findById(gId);
        Status s = statusService.findBysId(sId);

        inventory.setIMovedBy(e);
        inventory.setIInventoryGroup(ig);
        inventory.setIStatus(s);

        inventoryService.updateInventory(inventory);

        List<Inventory> i = inventoryService.findAllInventory();
        model.addAttribute("inventory", i);

        return"showinventory";
    }

    //remove an inventory
    @GetMapping("/remove/{iId}")
    public String removeInventory(@PathVariable("iId") Long id, Model model){

        inventoryService.removeInventory(id);

        List<Inventory> i = inventoryService.findAllInventory();
        model.addAttribute("inventory", i);

        return"showinventory";
    }

}
