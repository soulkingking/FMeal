package com.fragrantmeal.entity;

import java.io.Serializable;

/**
 * Created by CaoBin on 2016/3/16.
 */
public class User implements Serializable{
    private String userName;
    private String userNickName;
    private String passWord;
    private String userPhone;

    public User( String userPhone,String passWord) {
        this.userPhone = userPhone;
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
