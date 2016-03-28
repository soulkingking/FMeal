package com.fragrantmeal.entity;

import java.io.Serializable;

/**
 * Created by CaoBin on 2016/3/23.
 */
public class State  implements Serializable{
    private String loginState;

    public State(String loginState) {
        this.loginState = loginState;
    }

    public String getLoginState() {
        return loginState;
    }

    public void setLoginState(String loginState) {
        this.loginState = loginState;
    }
}
