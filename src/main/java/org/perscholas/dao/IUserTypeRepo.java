package org.perscholas.dao;

import org.perscholas.models.UserType;
import org.perscholas.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //makes this a repository
public interface IUserTypeRepo extends JpaRepository<UserType, Long> {
    Optional<UserType> findByuserTypeId(Long id);
}
