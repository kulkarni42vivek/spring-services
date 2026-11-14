package com.kulkarni42vivek.inventory_service.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kulkarni42vivek.inventory_service.model.Inventory;
import com.kulkarni42vivek.inventory_service.repo.InventoryRepo;

@Service
public class InventoryService {
	
	
	@Autowired
	InventoryRepo inventoryRepo;

//	public boolean isInStock(String orderCode) {
//		inventoryRepo.findByOrderCode();
//	}

	
}
