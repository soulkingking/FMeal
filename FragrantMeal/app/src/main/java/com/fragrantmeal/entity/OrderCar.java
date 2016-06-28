package com.fragrantmeal.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by CaoBin on 2016/4/24.
 */
public class OrderCar implements Serializable{
    private Map<SellerDish,Integer> cars;
    private Seller seller;

    public Map<SellerDish, Integer> getCars() {
        return cars;
    }

    public void setCars(Map<SellerDish, Integer> cars) {
        this.cars = cars;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
