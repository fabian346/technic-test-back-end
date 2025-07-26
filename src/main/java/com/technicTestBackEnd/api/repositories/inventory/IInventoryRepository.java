package com.technicTestBackEnd.api.repositories.inventory;

import com.technicTestBackEnd.api.model.entities.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventoryRepository extends JpaRepository<Inventory, Long> {
}
