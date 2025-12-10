package com.example.coupon_management.model;

import java.util.List;

public class Eligibility {
	
	private List<String> allowedUserTiers;
    private Double minLifetimeSpend;
    private Integer minOrdersPlaced;
    private Boolean firstOrderOnly;
    private List<String> allowedCountries;

    // Cart-based
    private Double minCartValue;
    private List<String> applicableCategories;
    private List<String> excludedCategories;
    private Integer minItemsCount;

    // getters & setters
    public List<String> getAllowedUserTiers() { return allowedUserTiers; }
    public void setAllowedUserTiers(List<String> allowedUserTiers) { this.allowedUserTiers = allowedUserTiers; }

    public Double getMinLifetimeSpend() { return minLifetimeSpend; }
    public void setMinLifetimeSpend(Double minLifetimeSpend) { this.minLifetimeSpend = minLifetimeSpend; }

    public Integer getMinOrdersPlaced() { return minOrdersPlaced; }
    public void setMinOrdersPlaced(Integer minOrdersPlaced) { this.minOrdersPlaced = minOrdersPlaced; }

    public Boolean getFirstOrderOnly() { return firstOrderOnly; }
    public void setFirstOrderOnly(Boolean firstOrderOnly) { this.firstOrderOnly = firstOrderOnly; }

    public List<String> getAllowedCountries() { return allowedCountries; }
    public void setAllowedCountries(List<String> allowedCountries) { this.allowedCountries = allowedCountries; }

    public Double getMinCartValue() { return minCartValue; }
    public void setMinCartValue(Double minCartValue) { this.minCartValue = minCartValue; }

    public List<String> getApplicableCategories() { return applicableCategories; }
    public void setApplicableCategories(List<String> applicableCategories) { this.applicableCategories = applicableCategories; }

    public List<String> getExcludedCategories() { return excludedCategories; }
    public void setExcludedCategories(List<String> excludedCategories) { this.excludedCategories = excludedCategories; }

    public Integer getMinItemsCount() { return minItemsCount; }
    public void setMinItemsCount(Integer minItemsCount) { this.minItemsCount = minItemsCount; }
}


