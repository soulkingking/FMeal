package com.fragrantmeal.entity;
/**
 * 送餐地址
 * @author Administrator
 */
public class DeliveryAddress {
	
	private String da_address;	//送餐地址
	private String da_id;		//送餐地址id
	private String da_name;		//点餐人姓名
	private String da_phone;	//点餐人手机号
	private String u_id;		//用户外键id
	private String user_alias;
	public DeliveryAddress() {
		
	}
	public DeliveryAddress(String da_address, String da_id, String da_name,
			String da_phone, String u_id, String user_alias) {
		super();
		this.da_address = da_address;
		this.da_id = da_id;
		this.da_name = da_name;
		this.da_phone = da_phone;
		this.u_id = u_id;
		this.user_alias = user_alias;
	}
	public String getDa_address() {
		return da_address;
	}
	public void setDa_address(String da_address) {
		this.da_address = da_address;
	}
	public String getDa_id() {
		return da_id;
	}
	public void setDa_id(String da_id) {
		this.da_id = da_id;
	}
	public String getDa_name() {
		return da_name;
	}
	public void setDa_name(String da_name) {
		this.da_name = da_name;
	}
	public String getDa_phone() {
		return da_phone;
	}
	public void setDa_phone(String da_phone) {
		this.da_phone = da_phone;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getUser_alias() {
		return user_alias;
	}
	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}
	
	

}
