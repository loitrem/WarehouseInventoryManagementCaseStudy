package org.perscholas.dao;

import org.perscholas.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //makes this a repository
public interface IStatusRepo extends JpaRepository<Status, Long> {
    Optional<Status> findBysId(int id);
}
