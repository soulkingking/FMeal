package com.fragrantmeal.entity;

import java.sql.Date;

public class FoodRingMembers {
	
	private String frm_id;			//美食圈成员主键
	private String fr_id;			//美食圈外键id
	private String fr_name;			//美食圈名称
	private Date fr_jointime;	//当前用户加入美食圈时间
	private String u_id;			//用户外键id
	private String user_alias;		//用户昵称
	private String user_icon;		//用户头像
	private String user_signature;		//用户个性签名
	
	public FoodRingMembers() {
		
	}
	
	public FoodRingMembers(String frm_id, String fr_id, String fr_name,
			Date fr_jointime, String u_id, String user_alias, String user_icon,
			String user_signature) {
		this.frm_id = frm_id;
		this.fr_id = fr_id;
		this.fr_name = fr_name;
		this.fr_jointime = fr_jointime;
		this.u_id = u_id;
		this.user_alias = user_alias;
		this.user_icon = user_icon;
		this.user_signature = user_signature;
	}

	public String getUser_icon() {
		return user_icon;
	}


	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}


	public String getUser_signature() {
		return user_signature;
	}


	public void setUser_signature(String user_signature) {
		this.user_signature = user_signature;
	}


	public String getFr_id() {
		return fr_id;
	}


	public Date getFr_jointime() {
		return fr_jointime;
	}


	public String getFr_name() {
		return fr_name;
	}

	public String getFrm_id() {
		return frm_id;
	}

	public String getU_id() {
		return u_id;
	}

	public String getUser_alias() {
		return user_alias;
	}

	public void setFr_id(String fr_id) {
		this.fr_id = fr_id;
	}
	public void setFr_jointime(Date fr_jointime) {
		this.fr_jointime = fr_jointime;
	}
	public void setFr_name(String fr_name) {
		this.fr_name = fr_name;
	}
	public void setFrm_id(String frm_id) {
		this.frm_id = frm_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}

}
