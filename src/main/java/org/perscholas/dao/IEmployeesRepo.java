package org.perscholas.dao;

import org.perscholas.models.Departments;
import org.perscholas.models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //makes this a repository
public interface IEmployeesRepo extends JpaRepository<Employees, Long> {
    Employees findByeId(Long id);
    Optional<Employees> findByeJobTitle(String title);
    List<Employees> findByeDepartment(Departments department);

}
