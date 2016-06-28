package com.fragrantmeal.entity;
/**
 * 餐厅评价
 * @author Administrator
 */
public class RestComment {
	
	private String rc_id;				//餐厅评价id
	private int rc_eat;					//饮食满意度
	private int rc_service;				//服务满意度
	private int rc_env;					//环境满意度
	private String rc_content;			//评价内容
	private String r_id;				//餐厅外键id
	private String rest_name;			//餐厅名称
	private String u_id;				//用户外键id
	private String user_alias;			//用户昵称
	
	public RestComment() {
		
	}
	
	public RestComment(String rc_id, int rc_eat, int rc_service, int rc_env,
			String rc_content, String r_id, String rest_name, String u_id,
			String user_alias) {
		this.rc_id = rc_id;
		this.rc_eat = rc_eat;
		this.rc_service = rc_service;
		this.rc_env = rc_env;
		this.rc_content = rc_content;
		this.r_id = r_id;
		this.rest_name = rest_name;
		this.u_id = u_id;
		this.user_alias = user_alias;
	}

	public String getR_id() {
		return r_id;
	}

	public String getRc_content() {
		return rc_content;
	}

	public int getRc_eat() {
		return rc_eat;
	}

	public int getRc_env() {
		return rc_env;
	}

	public String getRc_id() {
		return rc_id;
	}
	public int getRc_service() {
		return rc_service;
	}
	public String getRest_name() {
		return rest_name;
	}
	public String getU_id() {
		return u_id;
	}
	public String getUser_alias() {
		return user_alias;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	public void setRc_content(String rc_content) {
		this.rc_content = rc_content;
	}
	public void setRc_eat(int rc_eat) {
		this.rc_eat = rc_eat;
	}
	public void setRc_env(int rc_env) {
		this.rc_env = rc_env;
	}
	public void setRc_id(String rc_id) {
		this.rc_id = rc_id;
	}
	public void setRc_service(int rc_service) {
		this.rc_service = rc_service;
	}
	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}

}
