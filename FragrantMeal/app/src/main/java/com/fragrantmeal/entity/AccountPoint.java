package com.fragrantmeal.entity;

/**
 * 用户积分
 * @author Administrator
 */
public class AccountPoint {
	
	private int ap_curMonthDif;		//用户本月积分变化值
	private int ap_curpoint;		//用户当前积分
	private int ap_curWeekDif;		//用户本周积分变化值
	private String ap_id;			//积分id
	private int ap_maxMonthRank;	//用户积分最高月排行
	private int ap_maxpoint;		//用户最高积分
	private int ap_maxRank;			//用户历史最高积分
	private int ap_maxWeekRank;		//用户积分最高周排行
	private String u_id;			//用户表外键id
	private String user_alias;		//用户昵称
	private String user_icon;		//用户头像保存的相对路径
	
	public AccountPoint() {
		
	}

	public AccountPoint(int ap_curMonthDif, int ap_curpoint, int ap_curWeekDif,
			String ap_id, int ap_maxMonthRank, int ap_maxpoint, int ap_maxRank,
			int ap_maxWeekRank, String u_id, String user_alias, String user_icon) {
		this.ap_curMonthDif = ap_curMonthDif;
		this.ap_curpoint = ap_curpoint;
		this.ap_curWeekDif = ap_curWeekDif;
		this.ap_id = ap_id;
		this.ap_maxMonthRank = ap_maxMonthRank;
		this.ap_maxpoint = ap_maxpoint;
		this.ap_maxRank = ap_maxRank;
		this.ap_maxWeekRank = ap_maxWeekRank;
		this.u_id = u_id;
		this.user_alias = user_alias;
		this.user_icon = user_icon;
	}


	public int getAp_curMonthDif() {
		return ap_curMonthDif;
	}

	public int getAp_curpoint() {
		return ap_curpoint;
	}

	public int getAp_curWeekDif() {
		return ap_curWeekDif;
	}
	public String getAp_id() {
		return ap_id;
	}
	public int getAp_maxMonthRank() {
		return ap_maxMonthRank;
	}
	public int getAp_maxpoint() {
		return ap_maxpoint;
	}
	public int getAp_maxRank() {
		return ap_maxRank;
	}
	public int getAp_maxWeekRank() {
		return ap_maxWeekRank;
	}
	public String getU_id() {
		return u_id;
	}
	public String getUser_alias() {
		return user_alias;
	}
	public void setAp_curMonthDif(int ap_curMonthDif) {
		this.ap_curMonthDif = ap_curMonthDif;
	}
	public void setAp_curpoint(int ap_curpoint) {
		this.ap_curpoint = ap_curpoint;
	}
	public void setAp_curWeekDif(int ap_curWeekDif) {
		this.ap_curWeekDif = ap_curWeekDif;
	}
	public void setAp_id(String ap_id) {
		this.ap_id = ap_id;
	}
	public void setAp_maxMonthRank(int ap_maxMonthRank) {
		this.ap_maxMonthRank = ap_maxMonthRank;
	}
	public void setAp_maxpoint(int ap_maxpoint) {
		this.ap_maxpoint = ap_maxpoint;
	}
	public void setAp_maxRank(int ap_maxRank) {
		this.ap_maxRank = ap_maxRank;
	}
	public void setAp_maxWeekRank(int ap_maxWeekRank) {
		this.ap_maxWeekRank = ap_maxWeekRank;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}

	public String getUser_icon() {
		return user_icon;
	}


	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}
	
	
}
