package com.fragrantmeal.entity;

import java.io.Serializable;

/**
 * 外卖订单
 * @author Administrator
 */
public class TakeoutOrder implements Serializable {
	
	private String to_id;				//外卖订单id
	private int to_boxFee;				//餐盒费
	private int to_deliveryFee;			//配送费
	private String to_extra;			//额外要求
	private String to_name;				//下单人姓名
	private String to_phone;			//下单人手机号
	private String to_address;			//下单人地址	
	private String u_id;				//用户外键id
	private String s_id;				//商家外键id
	private String seller_name;
	private String user_alias;
	
	public TakeoutOrder() {

	}

	public TakeoutOrder(String s_id, int to_boxFee, int to_deliveryFee,
			String to_extra, String to_name, String to_phone,
			String to_address, String to_id, String u_id, String seller_name,
			String user_alias) {
		this.s_id = s_id;
		this.to_boxFee = to_boxFee;
		this.to_deliveryFee = to_deliveryFee;
		this.to_extra = to_extra;
		this.to_name = to_name;
		this.to_phone = to_phone;
		this.to_address = to_address;
		this.to_id = to_id;
		this.u_id = u_id;
		this.seller_name = seller_name;
		this.user_alias = user_alias;
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public int getTo_boxFee() {
		return to_boxFee;
	}

	public void setTo_boxFee(int to_boxFee) {
		this.to_boxFee = to_boxFee;
	}

	public int getTo_deliveryFee() {
		return to_deliveryFee;
	}

	public void setTo_deliveryFee(int to_deliveryFee) {
		this.to_deliveryFee = to_deliveryFee;
	}

	public String getTo_extra() {
		return to_extra;
	}

	public void setTo_extra(String to_extra) {
		this.to_extra = to_extra;
	}

	public String getTo_name() {
		return to_name;
	}

	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}

	public String getTo_phone() {
		return to_phone;
	}

	public void setTo_phone(String to_phone) {
		this.to_phone = to_phone;
	}

	public String getTo_address() {
		return to_address;
	}

	public void setTo_address(String to_address) {
		this.to_address = to_address;
	}

	public String getTo_id() {
		return to_id;
	}

	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getUser_alias() {
		return user_alias;
	}

	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}
	
	
}
