package com.example.coupon_management.model;

import java.time.LocalDate;
public class Coupon {
	
	private String code;
    private String description;
    private DiscountType discountType;
    private double discountValue;
    private Double maxDiscountAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer usageLimitPerUser;
    private Eligibility eligibility;

    // getters & setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public DiscountType getDiscountType() { return discountType; }
    public void setDiscountType(DiscountType discountType) { this.discountType = discountType; }

    public double getDiscountValue() { return discountValue; }
    public void setDiscountValue(double discountValue) { this.discountValue = discountValue; }

    public Double getMaxDiscountAmount() { return maxDiscountAmount; }
    public void setMaxDiscountAmount(Double maxDiscountAmount) { this.maxDiscountAmount = maxDiscountAmount; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Integer getUsageLimitPerUser() { return usageLimitPerUser; }
    public void setUsageLimitPerUser(Integer usageLimitPerUser) { this.usageLimitPerUser = usageLimitPerUser; }

    public Eligibility getEligibility() { return eligibility; }
    public void setEligibility(Eligibility eligibility) { this.eligibility = eligibility; }
}


