package com.fragrantmeal.entity;

import java.io.Serializable;
import java.util.List;

public class SellerDishData implements Serializable {
	private SellerMenuType sellerMenuType;
	private List<SellerDish> sellerDishs;
	
	public SellerMenuType getSellerMenuType() {
		return sellerMenuType;
	}
	public void setSellerMenuType(SellerMenuType sellerMenuType) {
		this.sellerMenuType = sellerMenuType;
	}
	public List<SellerDish> getSellerDishs() {
		return sellerDishs;
	}
	public void setSellerDishs(List<SellerDish> sellerDishs) {
		this.sellerDishs = sellerDishs;
	}
	
	
}
