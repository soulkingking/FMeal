package com.fragrantmeal.entity;

import java.io.Serializable;
import java.sql.Date;
/**
 * 订座订单状态
 * @author Administrator
 *
 */
public class SeatOrderStatus implements Serializable {
	
	private String sos_id;			//订座订单状态id
	private String sos_status;		//订座订单状态
	private Date sos_time;			//订座订单状态修改时间
	private String so_id;			//订座订单外键id
	private String u_id;     		//下单用户编号
	private String user_alias;		//下单用户昵称
	
	public SeatOrderStatus() {
		
	}

	public SeatOrderStatus(String sos_id, String sos_status, Date sos_time,
			String so_id, String u_id, String user_alias) {
		this.sos_id = sos_id;
		this.sos_status = sos_status;
		this.sos_time = sos_time;
		this.so_id = so_id;
		this.u_id = u_id;
		this.user_alias = user_alias;
	}

	public String getSo_id() {
		return so_id;
	}

	public String getSos_id() {
		return sos_id;
	}

	public String getSos_status() {
		return sos_status;
	}

	public Date getSos_time() {
		return sos_time;
	}

	public String getU_id() {
		return u_id;
	}

	public String getUser_alias() {
		return user_alias;
	}

	public void setSo_id(String so_id) {
		this.so_id = so_id;
	}

	public void setSos_id(String sos_id) {
		this.sos_id = sos_id;
	}

	public void setSos_status(String sos_status) {
		this.sos_status = sos_status;
	}

	public void setSos_time(Date sos_time) {
		this.sos_time = sos_time;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}

	
}