package org.perscholas.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.perscholas.models.Departments;
import org.perscholas.models.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Transactional
@SpringBootTest
@Slf4j
public class IEmployeesRepoTest {

    IEmployeesRepo iEmployeesRepo;
    IDepartmentsRepo iDepartmentsRepo;

    @Autowired
    public IEmployeesRepoTest(IEmployeesRepo iEmployeesRepo, IDepartmentsRepo iDepartmentsRepo) {
        this.iEmployeesRepo = iEmployeesRepo;
        this.iDepartmentsRepo = iDepartmentsRepo;
    }

    @BeforeAll
    static void beforeAll() {
        log.info("Beginning of testing for IEmployeesRepo");
    }

    @BeforeEach
    void setUp() {
        log.info("Start Test Method");
    }

    @Test
    public void getFindByeDepartment(){

        log.info("IEmployeesRepoTest:getFindByeepartment()");

        Departments d = iDepartmentsRepo.findBydId(99L);
        if (!iEmployeesRepo.findByeDepartment(d).isEmpty()){
            List<Employees> empList = iEmployeesRepo.findByeDepartment(d);
            Assert.notEmpty(empList, "Test to see if empList is empty");
            Assertions.assertFalse(empList.isEmpty());
        }
    }

    @Test
    public void getFindByeId(){

        log.info("IEmployeesRepoTest:getFindByeId()");

        Employees emp = iEmployeesRepo.findByeId(100L);
        Assertions.assertFalse(emp.getEFirstName().isBlank());
        Assertions.assertFalse(emp.getELastName().isBlank());
        Assertions.assertFalse(emp.getEEmail().isBlank());
        Assertions.assertFalse(emp.getEPhoneNumber().isBlank());
        Assertions.assertFalse(emp.getEJobTitle().isBlank());

    }

    @AfterEach
    void tearDown() {
        log.info("End Test Method");
    }

    @AfterAll
    static void afterAll() {
        log.info("End of testing for IEmployeesRepo");
    }
}

