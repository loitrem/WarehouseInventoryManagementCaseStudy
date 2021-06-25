package org.perscholas.dao;

import org.perscholas.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //makes this a repository
public interface IStatusRepo extends JpaRepository<Status, Long> {

    Status findBysId(Long id);

    Status findBysStatus(String status);
}
