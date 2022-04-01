package com.exercise.product.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.exercise.product.model.Product;
import com.exercise.product.service.ProductService;

@Component
public class ProductUtility {
	
	@Autowired
    ProductService productService;
	
	/*
	 * This method fetches products from DB
	 * Prepares the client VO from DB data
	 * Returns the details to the Controller.
	 */
	public List<ProductVO> getProducts() {
		List<ProductVO> productVOList = new ArrayList<ProductVO>();
		
		// Service call to fetch the details from DB
	    List<Product> productList =  productService.getProducts();
	    
	    ProductVO productVO = null;
	    Map<String, Double> priceMap = null;
	    
	    if(productList != null && productList.size() > 0) {
	    	for(Product prod : productList) {
	    		productVO = new ProductVO();
	    		priceMap = new HashMap<String, Double>();
	    		productVO.setProdId(prod.getProductId());
	    		productVO.setProdName(prod.getProductName());
	    		productVO.setProdDesc(prod.getProductDesc());
	    		
	    		priceMap.put(prod.getProductCurrency(), prod.getProductPrice());  
	    		productVO.setPriceMap(priceMap);
	    		
	    		productVOList.add(productVO);
	    	}
	    }
	    return productVOList;
	}

	/*
	 * This method fetches products from DB
	 * Makes a call to the Exchange Rate External API
	 * Prepares the client VO from DB data
	 * Returns the details to the Controller.
	 */
	public List<ProductVO> getProductsWithCurr() {
		List<ProductVO> productVOList = new ArrayList<ProductVO>();
		
		// Service call to fetch the details from DB
	    List<Product> productList =  productService.getProducts();
	    
	    String uri = "http://data.fixer.io/api/latest?access_key=b5d6d6de407990d8a4e1a3738c60e4b0";
	    // Move the hard coded uri to properties file
	    RestTemplate restTemplate = null; 
	    String exchangeRateStr = null;
	    JSONObject exchangeRateJson = null;
	    JSONObject exchangeRates = null;
	    		
		try { 
			restTemplate = new RestTemplate();
			exchangeRateStr = restTemplate.getForObject(uri, String.class);
			exchangeRateJson = new JSONObject(exchangeRateStr);
		} catch(HttpClientErrorException  ce){
			// read the exception desc and send back to the client
		} catch(HttpServerErrorException  se){
			// read the exception desc and send back to the client
		} catch(UnknownHttpStatusCodeException ue){
			// read the exception desc and send back to the client
		}
	    
        if(exchangeRateJson != null && exchangeRateJson.getBoolean("success") && exchangeRateJson.get("rates") != null )  {
	    	exchangeRates = (JSONObject) exchangeRateJson.get("rates");
	    }
	    	    
	    ProductVO productVO = null;
	    Map<String, Double> priceMap = null;
	    
	    if(productList != null && productList.size() > 0) {
	    	for(Product prod : productList) {
	    		productVO = new ProductVO();
	    		priceMap = new HashMap<String, Double>();
	    		productVO.setProdId(prod.getProductId());
	    		productVO.setProdName(prod.getProductName());
	    		productVO.setProdDesc(prod.getProductDesc());	    
	    		/* Added this piece of code to display the price value atleast in base currency as stored in the DB
	    		 * Even when exchange rates are not available
	    		 */
	    		priceMap.put(prod.getProductCurrency(), prod.getProductPrice());
	    		
	    		if(exchangeRates != null )  {
	    			priceMap.put("EUR", exchangeRates.has("EUR") ? prod.getProductPrice() * exchangeRates.getDouble("EUR") : 0.0);
	    	    	priceMap.put("GBP", exchangeRates.has("GBP") ? prod.getProductPrice() * exchangeRates.getDouble("GBP") : 0.0);
	    	    	priceMap.put("INR", exchangeRates.has("INR") ? prod.getProductPrice() * exchangeRates.getDouble("INR") : 0.0);
	    	    	priceMap.put("USD", exchangeRates.has("USD") ? prod.getProductPrice() * exchangeRates.getDouble("USD") : 0.0);
	    	    	// If any specific rate is not available, then return default value.
	    	    	priceMap.put("ZZT", exchangeRates.has("ZZT") ? prod.getProductPrice() * exchangeRates.getDouble("ZZT") : 0.0);
	    	    }    				    			
	    		// Write code to trim/roundoff the converted value as per requirement
	    		
	    		productVO.setPriceMap(priceMap);
	    		productVOList.add(productVO);
	    	}
	    }
	    return productVOList;
	}


}
