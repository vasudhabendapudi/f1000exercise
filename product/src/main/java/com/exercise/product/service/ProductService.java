package com.exercise.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.product.model.Product;
import com.exercise.product.repository.ProductRepository;


@Service
public class ProductService {

	  @Autowired
      ProductRepository productRepository;    
	  
	  /*
	   * This method returns the list of products from the database
	   */
	  public List<Product> getProducts() {
	      return productRepository.findAll();
	  }
	  
}
