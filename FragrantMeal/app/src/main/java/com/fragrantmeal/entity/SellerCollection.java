package com.fragrantmeal.entity;

import java.io.Serializable;

/**
 * 商家收藏
 * @author Administrator
 *
 */
public class SellerCollection implements Serializable {
	
	private String scoll_id;	//商家收藏id
	private String s_id;		//商家外键id
	private String u_id;		//收藏该商家的用户外键id
	private String seller_name;	//商家名称
	private String user_alias;	//用户昵称

	public SellerCollection() {
		
	}

	public SellerCollection(String scoll_id, String s_id, String u_id,
			String seller_name, String user_alias) {
		super();
		this.scoll_id = scoll_id;
		this.s_id = s_id;
		this.u_id = u_id;
		this.seller_name = seller_name;
		this.user_alias = user_alias;
	}

	public String getS_id() {
		return s_id;
	}

	public String getScoll_id() {
		return scoll_id;
	}
	public String getSeller_name() {
		return seller_name;
	}
	
	public String getU_id() {
		return u_id;
	}
	
	
	public String getUser_alias() {
		return user_alias;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public void setScoll_id(String scoll_id) {
		this.scoll_id = scoll_id;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}

}
