package org.perscholas.dao;

import org.perscholas.models.Departments;
import org.perscholas.models.Employees;
import org.perscholas.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //makes this a repository
public interface IEmployeesRepo extends JpaRepository<Employees, Long> {

    Employees findByeId(Long id);

    List<Employees> findByeDepartment(Departments department);

    //custom query
    @Query("SELECT e FROM Employees e WHERE e.eJobTitle LIKE %:title%")
    List<Employees> findByeJobTitle(@Param("title") String title);

    //custom query
    @Query("SELECT e FROM Employees e WHERE e.eUserId IS NOT NULL")
    List<Employees> findAllUserIdIfNotNull();

    Employees findByeUserId(Users u);


}
