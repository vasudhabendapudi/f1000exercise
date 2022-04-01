package com.exercise.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exercise.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	
}