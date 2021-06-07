package org.perscholas.dao;

import org.perscholas.models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //makes this a repository
public interface IEmployeesRepo extends JpaRepository<Employees, Long> {
    Optional<Employees> findByeId(int id);
    Optional<Employees> findByeJobTitle(String title);

}
