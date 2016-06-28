package com.fragrantmeal.entity;

import java.io.Serializable;

/**
 * Created by CaoBin on 2016/4/23.
 */
public class Car implements Serializable{
    private SellerDish sellerDish;
    private int num;

    public SellerDish getSellerDish() {
        return sellerDish;
    }

    public void setSellerDish(SellerDish sellerDish) {
        this.sellerDish = sellerDish;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
