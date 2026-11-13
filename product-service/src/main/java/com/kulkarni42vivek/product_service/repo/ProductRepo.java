package com.kulkarni42vivek.product_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kulkarni42vivek.product_service.model.Product;

public interface ProductRepo extends JpaRepository<Product, String>{

}
