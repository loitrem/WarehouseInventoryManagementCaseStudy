package org.perscholas.dao;

import org.perscholas.models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //makes this a repository
public interface IUserTypeRepo extends JpaRepository<UserType, Long> {

    UserType findByuserTypeId(Long id);

    UserType findByuserTypeName(String name);
}
