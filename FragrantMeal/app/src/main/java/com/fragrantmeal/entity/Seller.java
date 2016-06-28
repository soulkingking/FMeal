package com.fragrantmeal.entity;

import java.io.Serializable;

/**
 * 商家
 * 
 * @author Administrator
 */
public class Seller implements Serializable{

	private int seller_df;            // 商家配送费
	private String s_id;              // 商家id
	private String seller_icon;       // 商家图片地址
	private String seller_contact;    // 商家联系方式
	private int seller_degree;        // 商家好评度
	private int seller_deliverytime;  // 商家平均送达时间
	private String seller_name;       // 商家名称
	private String seller_notice;     // 商家公告
	private int seller_sendprice;     // 商家起送价
	private boolean seller_status;    // 商家营业状态
	private String seller_intro;      // 商家简介
	private String seller_starttime;    // 商家开始营业时间
	private String seller_endtime;      // 商家结束营业时间
	private double seller_longitude;
	private double seller_latitude;

	public Seller() {

	}
	public Seller(int seller_df, String s_id, String seller_icon,
			String seller_contact, int seller_degree, int seller_deliverytime,
			String seller_name, String seller_notice, int seller_sendprice,
			boolean seller_status, String seller_intro, String seller_starttime,
			String seller_endtime, double seller_longitude, double seller_latitude) {
		super();
		this.seller_df = seller_df;
		this.s_id = s_id;
		this.seller_icon = seller_icon;
		this.seller_contact = seller_contact;
		this.seller_degree = seller_degree;
		this.seller_deliverytime = seller_deliverytime;
		this.seller_name = seller_name;
		this.seller_notice = seller_notice;
		this.seller_sendprice = seller_sendprice;
		this.seller_status = seller_status;
		this.seller_intro = seller_intro;
		this.seller_starttime = seller_starttime;
		this.seller_endtime = seller_endtime;
		this.seller_longitude = seller_longitude;
		this.seller_latitude = seller_latitude;
	}



	public double getSeller_longitude() {
		return seller_longitude;
	}

	public void setSeller_longitude(double seller_longitude) {
		this.seller_longitude = seller_longitude;
	}

	public double getSeller_latitude() {
		return seller_latitude;
	}

	public void setSeller_latitude(double seller_latitude) {
		this.seller_latitude = seller_latitude;
	}

	public int getSeller_df() {
		return seller_df;
	}

	public void setSeller_df(int seller_df) {
		this.seller_df = seller_df;
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public String getSeller_icon() {
		return seller_icon;
	}

	public void setSeller_icon(String seller_icon) {
		this.seller_icon = seller_icon;
	}

	public String getSeller_contact() {
		return seller_contact;
	}

	public void setSeller_contact(String seller_contact) {
		this.seller_contact = seller_contact;
	}

	public int getSeller_degree() {
		return seller_degree;
	}

	public void setSeller_degree(int seller_degree) {
		this.seller_degree = seller_degree;
	}

	public int getSeller_deliverytime() {
		return seller_deliverytime;
	}

	public void setSeller_deliverytime(int seller_deliverytime) {
		this.seller_deliverytime = seller_deliverytime;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getSeller_notice() {
		return seller_notice;
	}

	public void setSeller_notice(String seller_notice) {
		this.seller_notice = seller_notice;
	}

	public int getSeller_sendprice() {
		return seller_sendprice;
	}

	public void setSeller_sendprice(int seller_sendprice) {
		this.seller_sendprice = seller_sendprice;
	}

	public boolean isSeller_status() {
		return seller_status;
	}

	public void setSeller_status(boolean seller_status) {
		this.seller_status = seller_status;
	}

	public String getSeller_intro() {
		return seller_intro;
	}

	public void setSeller_intro(String seller_intro) {
		this.seller_intro = seller_intro;
	}

	public String getSeller_starttime() {
		return seller_starttime;
	}

	public void setSeller_starttime(String seller_starttime) {
		this.seller_starttime = seller_starttime;
	}

	public String getSeller_endtime() {
		return seller_endtime;
	}

	public void setSeller_endtime(String seller_endtime) {
		this.seller_endtime = seller_endtime;
	}

}
