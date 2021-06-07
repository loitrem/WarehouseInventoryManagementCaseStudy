package org.perscholas.dao;

import org.perscholas.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository //makes this a repository
public interface IInventoryRepo extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByLocation(String location);
    Optional<Inventory> findByDateReceived(Date date);
    Optional<Inventory> findByInventoryGroup(int group);
    Optional<Inventory> findByStatus(int status);
    Optional<Inventory> findByItemNumber(int itemNumber);
}
