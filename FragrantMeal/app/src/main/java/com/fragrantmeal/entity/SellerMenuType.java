package com.fragrantmeal.entity;

import java.io.Serializable;

/**
 * 商家菜单类别
 * @author Administrator
 *
 */
public class SellerMenuType implements Serializable {
	
	private String s_id;			//商家外键id
	private String seller_name;		//商家名称
	private String smt_id;			//商家菜单类别id
	private String smt_name;		//商家菜单类别名称
	
	public SellerMenuType() {
	
	}
	public SellerMenuType(String s_id, String seller_name, String smt_id,
			String smt_name) {
		this.s_id = s_id;
		this.seller_name = seller_name;
		this.smt_id = smt_id;
		this.smt_name = smt_name;
	}
	public String getS_id() {
		return s_id;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public String getSmt_id() {
		return smt_id;
	}
	
	
	public String getSmt_name() {
		return smt_name;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public void setSmt_id(String smt_id) {
		this.smt_id = smt_id;
	}
	public void setSmt_name(String smt_name) {
		this.smt_name = smt_name;
	}

}
