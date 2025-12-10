package com.example.coupon_management.model;

public class UserContext {
	
	private String userId;
    private String userTier;
    private String country;
    private double lifetimeSpend;
    private int ordersPlaced;
    private int previousUsesOfThisCoupon; // for usageLimitPerUser

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserTier() { return userTier; }
    public void setUserTier(String userTier) { this.userTier = userTier; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public double getLifetimeSpend() { return lifetimeSpend; }
    public void setLifetimeSpend(double lifetimeSpend) { this.lifetimeSpend = lifetimeSpend; }

    public int getOrdersPlaced() { return ordersPlaced; }
    public void setOrdersPlaced(int ordersPlaced) { this.ordersPlaced = ordersPlaced; }

    public int getPreviousUsesOfThisCoupon() { return previousUsesOfThisCoupon; }
    public void setPreviousUsesOfThisCoupon(int previousUsesOfThisCoupon) { this.previousUsesOfThisCoupon = previousUsesOfThisCoupon; }
}


