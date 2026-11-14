package com.kulkarni42vivek.inventory_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kulkarni42vivek.inventory_service.model.InventoryResponse;
import com.kulkarni42vivek.inventory_service.repo.InventoryRepo;

@Service
public class InventoryService {
	
	
	@Autowired
	InventoryRepo inventoryRepo;

	public List<InventoryResponse> isInStock(List<String> orderCode) {
		List<InventoryResponse> response = inventoryRepo.findByOrderCodeIn(orderCode)
			.stream()
			.map(obj -> InventoryResponse
					.builder()
					.id(obj.getId())
					.orderCode(obj.getOrderCode())
					.quantity(obj.getQuantity())
					.isInStock(obj.getQuantity()>0)
					.build()
			)
			.collect(Collectors.toList());
		
		return response;
	}

	
}
