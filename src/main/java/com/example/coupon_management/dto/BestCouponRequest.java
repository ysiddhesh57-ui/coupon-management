package com.example.coupon_management.dto;

import com.example.coupon_management.model.UserContext;
import com.example.coupon_management.model.Cart;

public class BestCouponRequest {
	
	private UserContext user;
    private Cart cart;

    public UserContext getUser() {
        return user;
    }

    public void setUser(UserContext user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}


