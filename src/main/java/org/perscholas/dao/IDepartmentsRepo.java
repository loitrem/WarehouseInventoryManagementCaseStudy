package org.perscholas.dao;

import org.perscholas.models.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //makes this a repository
public interface IDepartmentsRepo extends JpaRepository<Departments, Long> {

    Departments findBydId(Long id);
}
