package com.fragrantmeal.entity;

import java.sql.Date;


/**
 * 美食圈
 * @author Administrator
 */
public class FoodRing {
	
	private Date fr_createtime;		//美食圈创建时间
	private String fr_icon;			//美食圈头像
	private String fr_id;			//美食圈id
	private String fr_name;			//美食圈名称
	private boolean fr_visible;		//对附近是否可见
	private String u_id;			//用户外键id(创建者id)
	private String user_alias;		//创建者昵称
	private String user_icon;		//创建者头像
	private String user_signature;	//创建者个性签名
	
	public FoodRing() {
		
	}

	public FoodRing(Date fr_createtime, String fr_icon, String fr_id,
			String fr_name, boolean fr_visible, String u_id, String user_alias,
			String user_icon, String user_signature) {
		this.fr_createtime = fr_createtime;
		this.fr_icon = fr_icon;
		this.fr_id = fr_id;
		this.fr_name = fr_name;
		this.fr_visible = fr_visible;
		this.u_id = u_id;
		this.user_alias = user_alias;
		this.user_icon = user_icon;
		this.user_signature = user_signature;
	}


	public String getUser_alias() {
		return user_alias;
	}

	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}

	public Date getFr_createtime() {
		return fr_createtime;
	}
	
	public String getFr_icon() {
		return fr_icon;
	}
	
	public String getFr_id() {
		return fr_id;
	}
	public String getFr_name() {
		return fr_name;
	}
	public String getU_id() {
		return u_id;
	}
	public boolean isFr_visible() {
		return fr_visible;
	}
	public void setFr_createtime(Date fr_createtime) {
		this.fr_createtime = fr_createtime;
	}
	public void setFr_icon(String fr_icon) {
		this.fr_icon = fr_icon;
	}
	public void setFr_id(String fr_id) {
		this.fr_id = fr_id;
	}
	public void setFr_name(String fr_name) {
		this.fr_name = fr_name;
	}
	public void setFr_visible(boolean fr_visible) {
		this.fr_visible = fr_visible;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
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
	
	
}
