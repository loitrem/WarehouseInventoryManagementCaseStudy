package org.perscholas.dao;

import org.perscholas.models.InventoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //makes this a repository
public interface IInventoryGroupRepo extends JpaRepository<InventoryGroup, Long> {
    InventoryGroup findBygId(Long id);
    Optional<InventoryGroup> findBygCompanyName(String companyName);
}
