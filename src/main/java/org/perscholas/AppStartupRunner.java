package org.perscholas;

import lombok.extern.java.Log;
import org.perscholas.dao.*;
import org.perscholas.models.*;
import org.perscholas.security.AppSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
@Log
@Transactional
public class AppStartupRunner implements CommandLineRunner{

    IEmployeesRepo iEmployeesRepo;
    IDepartmentsRepo iDepartmentsRepo;
    IInventoryRepo inventoryRepo;
    IInventoryGroupRepo iInventoryGroupRepo;
    IStatusRepo iStatusRepo;
    IUserRepo iUserRepo;
    IUserTypeRepo iUserTypeRepo;

    @Autowired
    public AppStartupRunner(IEmployeesRepo iEmployeesRepo, IDepartmentsRepo iDepartmentsRepo, IInventoryRepo inventoryRepo, IInventoryGroupRepo iInventoryGroupRepo, IStatusRepo iStatusRepo, IUserRepo iUserRepo, IUserTypeRepo iUserTypeRepo) {
        this.iEmployeesRepo = iEmployeesRepo;
        this.iDepartmentsRepo = iDepartmentsRepo;
        this.inventoryRepo = inventoryRepo;
        this.iInventoryGroupRepo = iInventoryGroupRepo;
        this.iStatusRepo = iStatusRepo;
        this.iUserRepo = iUserRepo;
        this.iUserTypeRepo = iUserTypeRepo;
    }

    @Override
    public void run(String... args) throws Exception {


        log.info("*************** START UserType SQL STATEMENTS ***************");

        UserType user = iUserTypeRepo.save(new UserType("ROLE_USER"));
        UserType authUser = iUserTypeRepo.save(new UserType("ROLE_AUTH_USER"));
        UserType admin = iUserTypeRepo.save(new UserType("ROLE_ADMIN"));

        log.info("*************** START Departments SQL STATEMENTS ***************");

        Departments mm = iDepartmentsRepo.save(new Departments("Material Movement"));
        Departments rec = iDepartmentsRepo.save(new Departments("Receiving"));
        Departments ship = iDepartmentsRepo.save(new Departments("Shipping"));

        log.info("*************** START Users SQL STATEMENTS ***************");

        Users loitrem = iUserRepo.save(new Users("loitrem", AppSecurityConfiguration.getPasswordEncoder().encode("NrBbhf!982"), admin));
        Users testbob = iUserRepo.save(new Users("testbob", AppSecurityConfiguration.getPasswordEncoder().encode("NrBbhf!982"), user));


        log.info("*************** START Employees SQL STATEMENTS ***************");

        Employees erik = iEmployeesRepo.save(new Employees("Erik","Kowalski","Manager","123456789","email@email.com",new Date(2021-06-14),new Date(2021-06-14), loitrem,mm));
        Employees bob = iEmployeesRepo.save(new Employees("Bob","Failsallot","Shipper","123456789","email2@email.com",new Date(2021-06-20),new Date(2021-06-20), testbob,rec));
        Employees george = iEmployeesRepo.save(new Employees("George","Testuser","Receiving","123456789","email3@email.com",new Date(2021-06-25),new Date(2021-06-25),null,ship));
        Employees fred = iEmployeesRepo.save(new Employees("Fred","McAwesome","Material Handler","123456789","email4@email.com",new Date(2021-06-27),new Date(2021-06-27), null,mm));



        log.info("*************** START Status SQL STATEMENTS ***************");

        Status inLoc = iStatusRepo.save(new Status("In location"));
        Status inRec = iStatusRepo.save(new Status("Received"));
        Status inShip = iStatusRepo.save(new Status("Shipped"));

        log.info("*************** START Inventory Group SQL STATEMENTS ***************");

        InventoryGroup bg = iInventoryGroupRepo.save(new InventoryGroup("Test Company","Bill","Gates","fake@email.com","123456789",new Date(2021-06-20),new Date(2021-06-20)));
        InventoryGroup tt = iInventoryGroupRepo.save(new InventoryGroup("Another Company","Ted","Turner","fake2@email.com","987654321",new Date(2021-06-25),new Date(2021-06-25)));

        log.info("*************** START Inventory SQL STATEMENTS ***************");

        inventoryRepo.save(new Inventory("A12", "1111", "Metal Sheets 1/4", 50, bg,new Date(2021-06-20), inLoc,fred, new Date(2021-06-20)));
        inventoryRepo.save(new Inventory("A14", "1112", "Metal Sheets 1/8", 75, bg,new Date(2021-06-20), inLoc,fred, new Date(2021-06-20)));
        inventoryRepo.save(new Inventory("C25", "1234", "Posters", 44, tt,new Date(2021-06-25), inRec,george, new Date(2021-06-25)));
        inventoryRepo.save(new Inventory("B5", "4321", "Wrapping Paper", 80, tt,new Date(2021-06-25), inLoc,fred, new Date(2021-06-25)));





    }
}
