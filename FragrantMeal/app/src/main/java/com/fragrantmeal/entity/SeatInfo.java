package com.fragrantmeal.entity;

import java.io.Serializable;

/**
 * 订座信息
 * @author Administrator
 */
public class SeatInfo implements Serializable {
	
	private String si_id;		//订座信息id
	private String si_name;		//订座人姓名
	private String si_phone;	//订座人手机号
	private String u_id;		//用户外键id
	private String user_alias;  //用户别名
	
	public SeatInfo() {
		super();
	}
	
	public SeatInfo(String si_id, String si_name, String si_phone, String u_id,
			String user_alias) {
		super();
		this.si_id = si_id;
		this.si_name = si_name;
		this.si_phone = si_phone;
		this.u_id = u_id;
		this.user_alias = user_alias;
	}

	public String getSi_id() {
		return si_id;
	}

	public void setSi_id(String si_id) {
		this.si_id = si_id;
	}

	public String getSi_name() {
		return si_name;
	}

	public void setSi_name(String si_name) {
		this.si_name = si_name;
	}

	public String getSi_phone() {
		return si_phone;
	}

	public void setSi_phone(String si_phone) {
		this.si_phone = si_phone;
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
