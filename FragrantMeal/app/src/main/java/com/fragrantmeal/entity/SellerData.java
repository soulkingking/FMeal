package com.fragrantmeal.entity;

import java.io.Serializable;
import java.util.List;

public class SellerData implements Serializable {
	private String s_id;
	private String seller_name;
	private List<SellerDishData> sellerDishDatas;
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public List<SellerDishData> getSellerDishDatas() {
		return sellerDishDatas;
	}
	public void setSellerDishDatas(List<SellerDishData> sellerDishDatas) {
		this.sellerDishDatas = sellerDishDatas;
	}



}
