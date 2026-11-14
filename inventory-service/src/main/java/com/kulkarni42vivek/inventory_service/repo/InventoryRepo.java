package com.kulkarni42vivek.inventory_service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kulkarni42vivek.inventory_service.model.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Long>{
	
	Optional<Inventory> findByOrderCode();
}
