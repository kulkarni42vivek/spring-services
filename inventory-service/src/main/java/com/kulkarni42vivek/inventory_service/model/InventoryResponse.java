package com.kulkarni42vivek.inventory_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class InventoryResponse {
	private Long id;
	private String orderCode; 
	private Integer quantity;
	private Boolean isInStock;
}
