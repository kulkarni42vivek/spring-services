package com.kulkarni42vivek.product_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kulkarni42vivek.product_service.model.Product;
import com.kulkarni42vivek.product_service.model.ProductRequest;
import com.kulkarni42vivek.product_service.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	ProductRepo productRepo;

	public void createProduct(ProductRequest request) {
		Product product = Product.builder()
				.id(request.getId())
				.name(request.getName())
				.description(request.getDescription())
				.price(request.getPrice())
				.build();
		productRepo.save(product);
	}

	public List<ProductRequest> getProduct() {
		List<Product> list =  productRepo.findAll();
		return list.stream().map(obj -> ProductRequest.builder()
				.id(obj.getId())
				.name(obj.getName())
				.description(obj.getDescription())
				.price(obj.getPrice())
				.build()).collect(Collectors.toList());
	}

}
