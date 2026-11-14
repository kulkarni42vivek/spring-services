package com.kulkarni42vivek.inventory_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kulkarni42vivek.inventory_service.model.InventoryResponse;
import com.kulkarni42vivek.inventory_service.service.InventoryService;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;
	
	@GetMapping("/{orderCode}")
	@ResponseStatus(HttpStatus.CREATED)
	public List<InventoryResponse> isInStock(@RequestParam List<String> orderCodes) {
		return inventoryService.isInStock(orderCodes);
	}
}
