package com.fragrantmeal.entity;

import java.io.Serializable;

/**
 * 餐厅菜单类别
 * @author Administrator
 */
public class RestMenuType implements Serializable {
	
	private String rmt_id;			//餐厅菜单类别id
	private String r_id;			//餐厅外键id
	private String r_name;			//餐厅名称
	private String rmt_name;	//菜单类别名称
	
	public RestMenuType() {
		
	}
	
	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public RestMenuType(String rmt_id, String r_id, String r_name,
			String rmt_name) {
		this.rmt_id = rmt_id;
		this.r_id = r_id;
		this.r_name = r_name;
		this.rmt_name = rmt_name;
	}

	public String getR_id() {
		return r_id;
	}
	public String getRmt_id() {
		return rmt_id;
	}
	public String getRmt_name() {
		return rmt_name;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	public void setRmt_id(String rmt_id) {
		this.rmt_id = rmt_id;
	}
	public void setRmt_name(String rmt_name) {
		this.rmt_name = rmt_name;
	}
	
}
