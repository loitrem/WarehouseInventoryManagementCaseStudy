package org.perscholas.dao;

import org.perscholas.models.Inventory;
import org.perscholas.models.InventoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //makes this a repository
public interface IInventoryGroupRepo extends JpaRepository<InventoryGroup, Long> {
    Optional<InventoryGroup> findBygId(int id);
    Optional<InventoryGroup> findBygCompanyAbbreviation(String abbreviation);
    Optional<InventoryGroup> findBygCompanyName(String companyName);
}
