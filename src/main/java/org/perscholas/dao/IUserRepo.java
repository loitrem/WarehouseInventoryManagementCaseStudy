package org.perscholas.dao;

import org.perscholas.models.Employees;
import org.perscholas.models.UserType;
import org.perscholas.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //makes this a repository
public interface IUserRepo extends JpaRepository<Users, Long> {

    Users findByuId(Long id);
    Users findByuUsername(String username);

    Users findByuEmployeeUsername(Employees employees);

    List<Users> findByuUserType(UserType userType);
}
