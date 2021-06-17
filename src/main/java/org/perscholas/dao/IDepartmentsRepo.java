package org.perscholas.dao;

import org.perscholas.models.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //makes this a repository
public interface IDepartmentsRepo extends JpaRepository<Departments, Long> {
    Optional<Departments> findBydId(Long id);
}
