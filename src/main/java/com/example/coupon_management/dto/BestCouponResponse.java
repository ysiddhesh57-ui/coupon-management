package com.example.coupon_management.dto;

import com.example.coupon_management.model.Coupon;

public class BestCouponResponse {
	
	private Coupon coupon;       // null if no coupon applies
    private double cartValue;
    private double discountAmount;
    private double finalAmount;

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public double getCartValue() {
        return cartValue;
    }

    public void setCartValue(double cartValue) {
        this.cartValue = cartValue;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }
}


