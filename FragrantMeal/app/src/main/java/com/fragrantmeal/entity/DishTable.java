package com.fragrantmeal.entity;

/**
 * 点菜表
 * 
 * @author Administrator
 */
public class DishTable {

	private int dt_total; 		// 当前菜肴总花费
	private int dt_count; 		// 当前菜肴的数量
	private String dt_id; 		// 点菜表id
	private String sd_id; 		// 商家菜肴外键
	private String to_id; 		// 外卖订单外键id
	private String sd_name;		//菜肴名称
	private String u_id;		//下单用户外键id
	private String user_alias;	//下单用户昵称
	
	public DishTable() {
	}
	
	public DishTable(int dt_total, int dt_count, String dt_id, String sd_id,
			String to_id, String sd_name, String u_id, String user_alias) {
		super();
		this.dt_total = dt_total;
		this.dt_count = dt_count;
		this.dt_id = dt_id;
		this.sd_id = sd_id;
		this.to_id = to_id;
		this.sd_name = sd_name;
		this.u_id = u_id;
		this.user_alias = user_alias;
	}

	public int getDt_total() {
		return dt_total;
	}

	public void setDt_total(int dt_total) {
		this.dt_total = dt_total;
	}

	public int getDt_count() {
		return dt_count;
	}

	public void setDt_count(int dt_count) {
		this.dt_count = dt_count;
	}

	public String getDt_id() {
		return dt_id;
	}
	public String getSd_id() {
		return sd_id;
	}
	

	public String getSd_name() {
		return sd_name;
	}


	public String getTo_id() {
		return to_id;
	}

	public String getU_id() {
		return u_id;
	}

	public String getUser_alias() {
		return user_alias;
	}

	public void setDt_id(String dt_id) {
		this.dt_id = dt_id;
	}

	public void setSd_id(String sd_id) {
		this.sd_id = sd_id;
	}

	public void setSd_name(String sd_name) {
		this.sd_name = sd_name;
	}

	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}


}
