package org.perscholas.dao;

import org.perscholas.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository //makes this a repository
public interface IInventoryRepo extends JpaRepository<Inventory, Long> {
    Inventory findByiLocation(String location);
    Optional<Inventory> findByiDateReceived(Date date);
    Optional<Inventory> findByiInventoryGroup(int group);
    Optional<Inventory> findByiStatus(int status);
    Optional<Inventory> findByiItemNumber(int itemNumber);
}
