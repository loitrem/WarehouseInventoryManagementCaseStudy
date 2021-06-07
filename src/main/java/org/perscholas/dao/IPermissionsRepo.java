package org.perscholas.dao;

import org.perscholas.models.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //makes this a repository
public interface IPermissionsRepo extends JpaRepository<Permissions, Long> {
    Optional<Permissions> findBypId(int id);
}
