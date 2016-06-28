package com.fragrantmeal.entity;

import java.io.Serializable;

/**
 * 商家菜肴
 * @author Administrator
 */
public class SellerDish implements Serializable{

	private String sd_id;			//商家菜肴id
	private String sd_icon;			//菜肴图片(地址)
	private String sd_name;			//商家菜肴名称
	private int sd_price;			//菜肴价格
	private int sd_saledCount;		//已销售数量
	private String smt_id;			//商家菜单类别外键id
	private String smt_name;		//商家菜单类别名称
	private String s_id;			//商家id
	private String seller_name;		//所属商家名称

	public SellerDish() {

	}

	public String getSmt_name() {
		return smt_name;
	}

	public void setSmt_name(String smt_name) {
		this.smt_name = smt_name;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getSd_icon() {
		return sd_icon;
	}


	public SellerDish(String sd_id, String sd_icon, String sd_name,
					  int sd_price, int sd_saledCount, String smt_id, String smt_name,
					  String s_id, String seller_name) {
		this.sd_id = sd_id;
		this.sd_icon = sd_icon;
		this.sd_name = sd_name;
		this.sd_price = sd_price;
		this.sd_saledCount = sd_saledCount;
		this.smt_id = smt_id;
		this.smt_name = smt_name;
		this.s_id = s_id;
		this.seller_name = seller_name;
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public String getSd_id() {
		return sd_id;
	}

	public String getSd_name() {
		return sd_name;
	}
	public int getSd_price() {
		return sd_price;
	}
	public int getSd_saledCount() {
		return sd_saledCount;
	}
	public String getSmt_id() {
		return smt_id;
	}
	public void setSd_icon(String sd_icon) {
		this.sd_icon = sd_icon;
	}
	public void setSd_id(String sd_id) {
		this.sd_id = sd_id;
	}
	public void setSd_name(String sd_name) {
		this.sd_name = sd_name;
	}
	public void setSd_price(int sd_price) {
		this.sd_price = sd_price;
	}
	public void setSd_saledCount(int sd_saledCount) {
		this.sd_saledCount = sd_saledCount;
	}
	public void setSmt_id(String smt_id) {
		this.smt_id = smt_id;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SellerDish that = (SellerDish) o;

		if (sd_price != that.sd_price) return false;
		if (sd_saledCount != that.sd_saledCount) return false;
		if (!sd_id.equals(that.sd_id)) return false;
		if (!sd_icon.equals(that.sd_icon)) return false;
		if (!sd_name.equals(that.sd_name)) return false;
		if (!smt_id.equals(that.smt_id)) return false;
		if (!smt_name.equals(that.smt_name)) return false;
		if (!s_id.equals(that.s_id)) return false;
		return seller_name.equals(that.seller_name);

	}

	@Override
	public int hashCode() {
		int result = sd_id.hashCode();
		result = 31 * result + sd_icon.hashCode();
		result = 31 * result + sd_name.hashCode();
		result = 31 * result + sd_price;
		result = 31 * result + sd_saledCount;
		result = 31 * result + smt_id.hashCode();
		result = 31 * result + smt_name.hashCode();
		result = 31 * result + s_id.hashCode();
		result = 31 * result + seller_name.hashCode();
		return result;
	}

}
