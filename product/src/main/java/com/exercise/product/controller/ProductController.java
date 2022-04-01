package com.exercise.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.product.utility.ProductUtility;
import com.exercise.product.utility.ProductVO;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
    ProductUtility productUtility;

	/* This API returns the list of all products from the database
	 */
	@RequestMapping(value="/productList", method=RequestMethod.GET)
	public List<ProductVO> getProducts() {
	    return productUtility.getProducts();
	}
	
	/* This API returns the list of all products from the database
	 * along with the price values in different currencies
	 */
	@RequestMapping(value="/productCurrList", method=RequestMethod.GET)
	public List<ProductVO> getProductsWithCurr() {
	    return productUtility.getProductsWithCurr();
	}

}
