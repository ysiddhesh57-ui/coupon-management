package com.example.coupon_management.controller;

import com.example.coupon_management.dto.BestCouponRequest;
import com.example.coupon_management.dto.BestCouponResponse;
import com.example.coupon_management.model.Coupon;
import com.example.coupon_management.service.CouponService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CouponController {
	
	private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    // Create / store a coupon
    @PostMapping("/coupons")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        Coupon saved = couponService.addCoupon(coupon);
        return ResponseEntity.ok(saved);
    }

    // Optional: list all coupons (for debugging)
    @GetMapping("/coupons")
    public List<Coupon> getAllCoupons() {
        return couponService.getAllCoupons();
    }

    // Find best coupon
    @PostMapping("/coupons/best")
    public ResponseEntity<BestCouponResponse> getBestCoupon(
            @RequestBody BestCouponRequest request) {

        BestCouponResponse response =
                couponService.findBestCoupon(request.getUser(), request.getCart());

        return ResponseEntity.ok(response);
    }
}

	
	


