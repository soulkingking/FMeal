package com.fragrantmeal.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
	
	private String u_id;				//id
	private int user_age;    			//年龄
	private String user_alias;      	//用户昵称
	private String user_icon;           //用户头像的地址
	private String user_sex;         	//性别
	private String user_signature;  	//个性签名
	private String username;			//用户名
	private String password;			//用户密码
	private String token;				//用户聊天令牌
	
	public UserInfo() {
		
	}
	
	public UserInfo(String u_id, int user_age, String user_alias,
			String user_icon, String user_sex, String user_signature,
			String username, String password, String token) {
		this.u_id = u_id;
		this.user_age = user_age;
		this.user_alias = user_alias;
		this.user_icon = user_icon;
		this.user_sex = user_sex;
		this.user_signature = user_signature;
		this.username = username;
		this.password = password;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public String getU_id() {
		return u_id;
	}

	public int getUser_age() {
		return user_age;
	}

	public String getUser_alias() {
		return user_alias;
	}

	public String getUser_icon() {
		return user_icon;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public String getUser_signature() {
		return user_signature;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}
	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}
	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public void setUser_signature(String user_signature) {
		this.user_signature = user_signature;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
