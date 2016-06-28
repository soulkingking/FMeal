package com.fragrantmeal.entity;

import java.sql.Time;

/**
 * 餐厅类
 * @author Administrator
 *
 */
public class Restaurant {
	
	private String r_id;			//餐厅id
	private String r_name;		//餐厅名称
	private String r_icon;		//餐厅图片地址
	private int r_degree;		//餐厅综合好评度
	private String r_address;	//餐厅地址
	private Time r_starttime;	//餐厅开始营业时间
	private Time r_endtime;		//餐厅结束营业时间
	private int r_advandays;	//可提前订座的天数
	private String r_intro;		//餐厅简介
	private String r_contact;	//餐厅联系电话
	private boolean r_status;	//餐厅营业状态
	private String r_notice;	//餐厅公告 
	private double r_longitude;
	private double r_latitude;
	
	public Restaurant() {
		
	}
	
	public Restaurant(String r_id, String r_name, String r_icon, int r_degree,
			String r_address, Time r_starttime, Time r_endtime,
			int r_advandays, String r_intro, String r_contact,
			boolean r_status, String r_notice, double r_longitude,
			double r_latitude) {
		super();
		this.r_id = r_id;
		this.r_name = r_name;
		this.r_icon = r_icon;
		this.r_degree = r_degree;
		this.r_address = r_address;
		this.r_starttime = r_starttime;
		this.r_endtime = r_endtime;
		this.r_advandays = r_advandays;
		this.r_intro = r_intro;
		this.r_contact = r_contact;
		this.r_status = r_status;
		this.r_notice = r_notice;
		this.r_longitude = r_longitude;
		this.r_latitude = r_latitude;
	}



	public double getR_longitude() {
		return r_longitude;
	}

	public void setR_longitude(double r_longitude) {
		this.r_longitude = r_longitude;
	}

	public double getR_latitude() {
		return r_latitude;
	}

	public void setR_latitude(double r_latitude) {
		this.r_latitude = r_latitude;
	}

	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	
	public String getR_address() {
		return r_address;
	}
	
	public int getR_advandays() {
		return r_advandays;
	}
	
	public String getR_contact() {
		return r_contact;
	}
	public int getR_degree() {
		return r_degree;
	}
	public Time getR_endtime() {
		return r_endtime;
	}
	public String getR_icon() {
		return r_icon;
	}
	public String getR_id() {
		return r_id;
	}
	public String getR_intro() {
		return r_intro;
	}
	public String getR_notice() {
		return r_notice;
	}
	public Time getR_starttime() {
		return r_starttime;
	}
	public boolean isR_status() {
		return r_status;
	}
	public void setR_address(String r_address) {
		this.r_address = r_address;
	}
	public void setR_advandays(int r_advandays) {
		this.r_advandays = r_advandays;
	}
	public void setR_contact(String r_contact) {
		this.r_contact = r_contact;
	}
	public void setR_degree(int r_degree) {
		this.r_degree = r_degree;
	}
	public void setR_endtime(Time r_endtime) {
		this.r_endtime = r_endtime;
	}
	public void setR_icon(String r_icon) {
		this.r_icon = r_icon;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	public void setR_intro(String r_intro) {
		this.r_intro = r_intro;
	}
	public void setR_notice(String r_notice) {
		this.r_notice = r_notice;
	}
	public void setR_starttime(Time r_starttime) {
		this.r_starttime = r_starttime;
	}
	public void setR_status(boolean r_status) {
		this.r_status = r_status;
	}

}
