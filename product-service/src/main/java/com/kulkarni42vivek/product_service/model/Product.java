package com.kulkarni42vivek.product_service.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
@Getter
@Setter
@Builder
public class Product {

	@Id
	private String id ;
	private String name;
	private String description;
	private BigDecimal price ;
}
