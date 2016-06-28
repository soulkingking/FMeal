package com.fragrantmeal.entity;

import java.sql.Date;

/**
 * 用户反馈
 * @author Administrator
 */
public class Feedback {
	
	private String fb_id;		//用户反馈id
	private String fb_content;	//反馈的内容
	private Date fb_date;		//反馈的时间
	private String u_id;		//发送反馈的用户外键id
	private String user_alias;  //用户别名
	
	public Feedback() {
		
	}
	
	public Feedback(String fb_id, String fb_content, Date fb_date, String u_id,
			String user_alias) {
		super();
		this.fb_id = fb_id;
		this.fb_content = fb_content;
		this.fb_date = fb_date;
		this.u_id = u_id;
		this.user_alias = user_alias;
	}

	public String getFb_id() {
		return fb_id;
	}

	public void setFb_id(String fb_id) {
		this.fb_id = fb_id;
	}

	public String getFb_content() {
		return fb_content;
	}

	public void setFb_content(String fb_content) {
		this.fb_content = fb_content;
	}

	public Date getFb_date() {
		return fb_date;
	}

	public void setFb_date(Date fb_date) {
		this.fb_date = fb_date;
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
