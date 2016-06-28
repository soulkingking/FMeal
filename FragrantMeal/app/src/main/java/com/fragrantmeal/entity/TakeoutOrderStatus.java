package com.fragrantmeal.entity;

import java.io.Serializable;

public class TakeoutOrderStatus implements Serializable {

	private String tos_id;
	private String tos_status;
	private String tos_time;
	private String to_id;
	private String u_id;
	private String user_alias;

	public TakeoutOrderStatus() {

	}

	public TakeoutOrderStatus(String tos_id, String tos_status, String tos_time,
							  String to_id, String u_id, String user_alias) {
		this.tos_id = tos_id;
		this.tos_status = tos_status;
		this.tos_time = tos_time;
		this.to_id = to_id;
		this.u_id = u_id;
		this.user_alias = user_alias;
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

	public String getTos_id() {
		return tos_id;
	}

	public void setTos_id(String tos_id) {
		this.tos_id = tos_id;
	}

	public String getTos_status() {
		return tos_status;
	}

	public void setTos_status(String tos_status) {
		this.tos_status = tos_status;
	}

	public String getTos_time() {
		return tos_time;
	}

	public void setTos_time(String tos_time) {
		this.tos_time = tos_time;
	}

	public String getTo_id() {
		return to_id;
	}

	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}


}
