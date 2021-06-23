package org.perscholas.dao;

import org.perscholas.models.InventoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //makes this a repository
public interface IInventoryGroupRepo extends JpaRepository<InventoryGroup, Long> {
    InventoryGroup findBygId(Long id);
    @Query("SELECT i FROM InventoryGroup i WHERE i.gCompanyName LIKE %:name%")
    List<InventoryGroup> findBygCompanyName(@Param("name") String companyName);
}
