package com.fragrantmeal.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 订座订单
 * @author Administrator
 */

public class SeatOrder implements Serializable {
	
	private String so_id;			//订座订单id
	private Timestamp so_eatTime;		//就餐时间
	private int so_count;			//就餐人数
	private String so_extra;		//额外要求
	private String so_name;			//下单人姓名
	private String so_phone;		//下单人手机号
	private String r_id;			//餐厅外键id
	private String u_id;			//订座用户外键id
	private String r_name;          //餐厅名
	private String user_alias;      // 用户别名
	
	public SeatOrder() {
		
	}

	public SeatOrder(String so_id, Timestamp so_eatTime, int so_count,
			String so_extra, String so_name, String so_phone, String r_id,
			String u_id, String r_name, String user_alias) {
		super();
		this.so_id = so_id;
		this.so_eatTime = so_eatTime;
		this.so_count = so_count;
		this.so_extra = so_extra;
		this.so_name = so_name;
		this.so_phone = so_phone;
		this.r_id = r_id;
		this.u_id = u_id;
		this.r_name = r_name;
		this.user_alias = user_alias;
	}

	public String getSo_id() {
		return so_id;
	}

	public void setSo_id(String so_id) {
		this.so_id = so_id;
	}

	public Timestamp getSo_eatTime() {
		return so_eatTime;
	}

	public void setSo_eatTime(Timestamp so_eatTime) {
		this.so_eatTime = so_eatTime;
	}

	public int getSo_count() {
		return so_count;
	}

	public void setSo_count(int so_count) {
		this.so_count = so_count;
	}

	public String getSo_extra() {
		return so_extra;
	}

	public void setSo_extra(String so_extra) {
		this.so_extra = so_extra;
	}

	public String getSo_name() {
		return so_name;
	}

	public void setSo_name(String so_name) {
		this.so_name = so_name;
	}

	public String getSo_phone() {
		return so_phone;
	}

	public void setSo_phone(String so_phone) {
		this.so_phone = so_phone;
	}

	public String getR_id() {
		return r_id;
	}

	public void setR_id(String r_id) {
		this.r_id = r_id;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public String getUser_alias() {
		return user_alias;
	}

	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}

	
}
