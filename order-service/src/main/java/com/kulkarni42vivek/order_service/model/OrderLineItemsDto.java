package com.kulkarni42vivek.order_service.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Builder
public class OrderLineItemsDto {
	private Long id;
	private String orderCode;
	private BigDecimal price;
}
