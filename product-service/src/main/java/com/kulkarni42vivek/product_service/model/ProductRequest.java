package com.kulkarni42vivek.product_service.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductRequest {

	private String id ;
	private String name;
	private String description;
	private BigDecimal price ;
}
