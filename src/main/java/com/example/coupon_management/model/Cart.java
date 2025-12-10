package com.example.coupon_management.model;

import java.util.List;

public class Cart {
	
	 private List<CartItem> items;

	    public List<CartItem> getItems() { return items; }
	    public void setItems(List<CartItem> items) { this.items = items; }

	    public double getCartValue() {
	        if (items == null) return 0.0;
	        return items.stream()
	                .mapToDouble(i -> i.getUnitPrice() * i.getQuantity())
	                .sum();
	    }

	    public int getTotalItemsCount() {
	        if (items == null) return 0;
	        return items.stream()
	                .mapToInt(CartItem::getQuantity)
	                .sum();
	    }
	}


