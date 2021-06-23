package org.perscholas.dao;

import org.perscholas.models.Employees;
import org.perscholas.models.Inventory;
import org.perscholas.models.InventoryGroup;
import org.perscholas.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //makes this a repository
public interface IInventoryRepo extends JpaRepository<Inventory, Long> {
    Inventory findByiId(Long id);
    Inventory findByiLocation(String location);
    //custom query
    @Query("SELECT i FROM Inventory i WHERE i.iDescription LIKE %:desc%")
    List<Inventory> findByiDescription(@Param("desc") String description);
    List<Inventory> findByiInventoryGroup(InventoryGroup group);
    List<Inventory> findByiStatus(Status status);
    List<Inventory> findByiItemNumber(String itemNumber);
    Inventory findByiMovedBy(Employees e);
}
