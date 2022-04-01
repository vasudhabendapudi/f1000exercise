package com.exercise.product.utility;

import java.util.Map;

/*
 * ProductVO class to be exposed to client. 
 * This is created to avoid exposing the model class properties to the client layer.
 */
public class ProductVO {
	
	private long prodId;
	private String prodName;
	private String prodDesc;
	private Map<String, Double> priceMap;
	
	/**
	 * @return the prodId
	 */
	public long getProdId() {
		return prodId;
	}
	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(long prodId) {
		this.prodId = prodId;
	}
	/**
	 * @return the prodName
	 */
	public String getProdName() {
		return prodName;
	}
	/**
	 * @param prodName the prodName to set
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	/**
	 * @return the prodDesc
	 */
	public String getProdDesc() {
		return prodDesc;
	}
	/**
	 * @param prodDesc the prodDesc to set
	 */
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	/**
	 * @return the priceMap
	 */
	public Map<String, Double> getPriceMap() {
		return priceMap;
	}
	/**
	 * @param priceMap the priceMap to set
	 */
	public void setPriceMap(Map<String, Double> priceMap) {
		this.priceMap = priceMap;
	}

}
