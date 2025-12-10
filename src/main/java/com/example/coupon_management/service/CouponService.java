package com.example.coupon_management.service;

import com.example.coupon_management.dto.BestCouponResponse;
import com.example.coupon_management.model.*;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CouponService {
	
	 private final List<Coupon> coupons = new ArrayList<>();

	    public Coupon addCoupon(Coupon coupon) {
	        // ensure unique code: overwrite if exists
	        coupons.removeIf(c -> c.getCode().equalsIgnoreCase(coupon.getCode()));
	        coupons.add(coupon);
	        return coupon;
	    }

	    public List<Coupon> getAllCoupons() {
	        return coupons;
	    }

	    public BestCouponResponse findBestCoupon(UserContext user, Cart cart) {
	        double cartValue = cart.getCartValue();
	        Coupon bestCoupon = null;
	        double bestDiscount = 0.0;

	        LocalDate today = LocalDate.now();

	        for (Coupon coupon : coupons) {
	            if (!isWithinValidity(coupon, today)) {
	                continue;
	            }

	            if (!withinUsageLimit(coupon, user)) {
	                continue;
	            }

	            if (!matchesEligibility(coupon.getEligibility(), user, cart, cartValue)) {
	                continue;
	            }

	            double discount = calculateDiscount(coupon, cartValue);
	            if (discount <= 0) {
	                continue;
	            }

	            if (bestCoupon == null || isBetterCoupon(coupon, discount, bestCoupon, bestDiscount)) {
	                bestCoupon = coupon;
	                bestDiscount = discount;
	            }
	        }

	        BestCouponResponse response = new BestCouponResponse();
	        response.setCartValue(cartValue);
	        response.setDiscountAmount(bestDiscount);
	        response.setCoupon(bestCoupon);
	        response.setFinalAmount(cartValue - bestDiscount);
	        return response;
	    }

	    private boolean isWithinValidity(Coupon coupon, LocalDate today) {
	        if (coupon.getStartDate() != null && today.isBefore(coupon.getStartDate())) {
	            return false;
	        }
	        if (coupon.getEndDate() != null && today.isAfter(coupon.getEndDate())) {
	            return false;
	        }
	        return true;
	    }

	    private boolean withinUsageLimit(Coupon coupon, UserContext user) {
	        if (coupon.getUsageLimitPerUser() == null) {
	            return true;
	        }
	        return user.getPreviousUsesOfThisCoupon() < coupon.getUsageLimitPerUser();
	    }

	    private boolean matchesEligibility(
	            Eligibility eligibility,
	            UserContext user,
	            Cart cart,
	            double cartValue
	    ) {
	        if (eligibility == null) {
	            return true; // no conditions
	        }

	        // user-based
	        if (eligibility.getAllowedUserTiers() != null &&
	                !eligibility.getAllowedUserTiers().isEmpty()) {
	            if (!eligibility.getAllowedUserTiers().contains(user.getUserTier())) {
	                return false;
	            }
	        }

	        if (eligibility.getMinLifetimeSpend() != null &&
	                user.getLifetimeSpend() < eligibility.getMinLifetimeSpend()) {
	            return false;
	        }

	        if (eligibility.getMinOrdersPlaced() != null &&
	                user.getOrdersPlaced() < eligibility.getMinOrdersPlaced()) {
	            return false;
	        }

	        if (Boolean.TRUE.equals(eligibility.getFirstOrderOnly())) {
	            // first order => no previous completed orders
	            if (user.getOrdersPlaced() > 0) {
	                return false;
	            }
	        }

	        if (eligibility.getAllowedCountries() != null &&
	                !eligibility.getAllowedCountries().isEmpty()) {
	            if (!eligibility.getAllowedCountries().contains(user.getCountry())) {
	                return false;
	            }
	        }

	        // cart-based
	        if (eligibility.getMinCartValue() != null &&
	                cartValue < eligibility.getMinCartValue()) {
	            return false;
	        }

	        if (eligibility.getApplicableCategories() != null &&
	                !eligibility.getApplicableCategories().isEmpty()) {

	            boolean hasApplicable = cart.getItems().stream()
	                    .anyMatch(item -> eligibility.getApplicableCategories()
	                            .contains(item.getCategory()));

	            if (!hasApplicable) {
	                return false;
	            }
	        }

	        if (eligibility.getExcludedCategories() != null &&
	                !eligibility.getExcludedCategories().isEmpty()) {

	            boolean hasExcluded = cart.getItems().stream()
	                    .anyMatch(item -> eligibility.getExcludedCategories()
	                            .contains(item.getCategory()));

	            if (hasExcluded) {
	                return false;
	            }
	        }

	        if (eligibility.getMinItemsCount() != null &&
	                cart.getTotalItemsCount() < eligibility.getMinItemsCount()) {
	            return false;
	        }

	        return true;
	    }

	    private double calculateDiscount(Coupon coupon, double cartValue) {
	        if (coupon.getDiscountType() == DiscountType.FLAT) {
	            return coupon.getDiscountValue();
	        } else if (coupon.getDiscountType() == DiscountType.PERCENT) {
	            double d = cartValue * coupon.getDiscountValue() / 100.0;
	            if (coupon.getMaxDiscountAmount() != null &&
	                    d > coupon.getMaxDiscountAmount()) {
	                d = coupon.getMaxDiscountAmount();
	            }
	            return d;
	        }
	        return 0.0;
	    }

	    private boolean isBetterCoupon(
	            Coupon newCoupon,
	            double newDiscount,
	            Coupon currentBest,
	            double currentDiscount
	    ) {
	        if (newDiscount > currentDiscount) {
	            return true;
	        }
	        if (Double.compare(newDiscount, currentDiscount) == 0) {
	            // tie -> earliest endDate
	            LocalDate endNew = newCoupon.getEndDate();
	            LocalDate endOld = currentBest.getEndDate();

	            if (endNew != null && endOld != null) {
	                if (endNew.isBefore(endOld)) {
	                    return true;
	                } else if (endNew.isEqual(endOld)) {
	                    // tie -> lexicographically smaller code
	                    return newCoupon.getCode().compareTo(currentBest.getCode()) < 0;
	                } else {
	                    return false;
	                }
	            }
	            // if one of them has null endDate, keep existing
	            return false;
	        }
	        return false;
	    }
	}


