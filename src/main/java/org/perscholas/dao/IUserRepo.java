package org.perscholas.dao;

import org.perscholas.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository //makes this a repository
public interface IUserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByuId(int id);
    Optional<Users> findByuUsername(String username);
    Optional<Users> findByuUserType(int userType);
}
