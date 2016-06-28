package com.fragrantmeal.entity;

import java.io.Serializable;

public class RestDish implements Serializable {

	private String rd_id;		//餐厅菜肴id
	private String rd_icon;		//餐厅图片(地址)
	private String rd_name;		//餐厅菜肴名称
	private int rd_price;		//餐厅菜肴价格
	private String rmt_id;		//餐厅菜单类别外键id
	private String rmt_name;	//餐厅菜单类别名称
	private String r_id;		//餐厅id
	private String r_name;		//所属餐厅名称
	
	public RestDish() {
		super();
	}
	
	public RestDish(String rd_id, String rd_icon, String rd_name, int rd_price,
			String rmt_id, String rmt_name, String r_id, String r_name) {
		this.rd_id = rd_id;
		this.rd_icon = rd_icon;
		this.rd_name = rd_name;
		this.rd_price = rd_price;
		this.rmt_id = rmt_id;
		this.rmt_name = rmt_name;
		this.r_id = r_id;
		this.r_name = r_name;
	}

	public String getR_id() {
		return r_id;
	}

	public String getR_name() {
		return r_name;
	}

	public String getRd_icon() {
		return rd_icon;
	}

	public String getRd_id() {
		return rd_id;
	}

	public String getRd_name() {
		return rd_name;
	}

	public int getRd_price() {
		return rd_price;
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
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public void setRd_icon(String rd_icon) {
		this.rd_icon = rd_icon;
	}
	public void setRd_id(String rd_id) {
		this.rd_id = rd_id;
	}
	public void setRd_name(String rd_name) {
		this.rd_name = rd_name;
	}
	public void setRd_price(int rd_price) {
		this.rd_price = rd_price;
	}
	public void setRmt_id(String rmt_id) {
		this.rmt_id = rmt_id;
	}
	public void setRmt_name(String rmt_name) {
		this.rmt_name = rmt_name;
	}
	
}
