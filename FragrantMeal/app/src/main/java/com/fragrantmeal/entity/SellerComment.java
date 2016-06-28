package com.fragrantmeal.entity;

import java.io.Serializable;

/**
 * 商家评价
 * @author Administrator
 */
public class SellerComment implements Serializable {

	private String sc_id;			//商家评价表id
	private int sc_eat;				//商家饮食满意度
	private int sc_service;			//商家服务满意度
	private String sc_content;		//评价内容
	private String s_id;			//商家id
	private String seller_name;		//商家名称
	private String u_id;			//用户id
	private String user_alias;		//用户昵称
	private String to_id;

	public SellerComment() {

	}

	public SellerComment(String sc_id, int sc_eat, int sc_service,
						 String sc_content, String s_id, String seller_name, String u_id,
						 String user_alias) {
		this.sc_id = sc_id;
		this.sc_eat = sc_eat;
		this.sc_service = sc_service;
		this.sc_content = sc_content;
		this.s_id = s_id;
		this.seller_name = seller_name;
		this.u_id = u_id;
		this.user_alias = user_alias;
	}

	public String getS_id() {
		return s_id;
	}

	public String getSc_content() {
		return sc_content;
	}

	public int getSc_eat() {
		return sc_eat;
	}

	public String getSc_id() {
		return sc_id;
	}

	public int getSc_service() {
		return sc_service;
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
	public void setSc_content(String sc_content) {
		this.sc_content = sc_content;
	}
	public void setSc_eat(int sc_eat) {
		this.sc_eat = sc_eat;
	}
	public void setSc_id(String sc_id) {
		this.sc_id = sc_id;
	}
	public void setSc_service(int sc_service) {
		this.sc_service = sc_service;
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
	public String getTo_id() {
		return to_id;
	}
	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}

}
