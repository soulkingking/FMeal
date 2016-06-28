package com.fragrantmeal.entity;
/**
 * 餐厅收藏
 * @author Administrator
 */
public class RestCollection {
	
	private String rcoll_id;	//餐厅收藏id
	private String u_id;		//用户外键id
	private String user_alias;	//用户昵称
	private String r_id;		//餐厅外键id
	private String rest_name;	//餐厅名称
	
	public RestCollection() {
		
	}
	
	public RestCollection(String rcoll_id, String u_id, String user_alias,
			String r_id, String rest_name) {
		this.rcoll_id = rcoll_id;
		this.u_id = u_id;
		this.user_alias = user_alias;
		this.r_id = r_id;
		this.rest_name = rest_name;
	}

	public String getR_id() {
		return r_id;
	}

	public String getRcoll_id() {
		return rcoll_id;
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
	public void setRcoll_id(String rcoll_id) {
		this.rcoll_id = rcoll_id;
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
