# Coupon Managemeent - Backend Service

A backend service that evaluates the **best applicable coupon** for a user's cart based on discount rules and eligibility conditions.

This project is built using **Spring Boot**, following a clean layered architecture:
Controller → Service → Model → DTO.

---

## 1. Tech Stack

- **Java:** 17  
- **Framework:** Spring Boot 3  
- **Build Tool:** Maven  
- **Architecture:** REST API (Monolithic)  
- **Testing Tool:** Postman  
- **Base Package:** `com.example.coupon_management`

 ---

 ## 2. How to Run the Project

 ### Prerequisites
 - Java 17  
- Maven  
- IDE (STS4 / IntelliJ / VS Code)

### Running the project

From terminal

```bash
mvn spring-boot:run
```
- Or run the main class manually:
 com.example.coupon_management.CouponManagementApplication
- Application will start at:
  http://localhost:9090

---

## 3. API Endpoints

### 3.1 Create or Update Coupon

**Post**/api/coupon

Adds or updates a coupon in the in-memory list.

**Example Request**

```Json
{
  "code": "WELCOME100",
  "description": "Flat 100 off for new users",
  "discountType": "FLAT",
  "discountValue": 100,
  "maxDiscountAmount": null,
  "startDate": "2025-12-01",
  "endDate": "2025-12-31",
  "usageLimitPerUser": 1,
  "eligibility": {
    "allowedUserTiers": ["NEW"],
    "minLifetimeSpend": 0,
    "minOrdersPlaced": 0,
    "firstOrderOnly": true,
    "allowedCountries": ["IN"],
    "minCartValue": 500,
    "applicableCategories": ["electronics", "fashion"],
    "excludedCategories": [],
    "minItemsCount": 1
  }
}
```

### 3.2 List All Coupon

**GET**/api/coupon

Return all coupons stored in memory.

**Example Response**

```Json
[
  {
    "code": "WELCOME100",
    "discountType": "FLAT",
    "discountValue": 100
  },
  {
    "code": "ELEC10",
    "discountType": "PERCENT",
    "discountValue": 10
  }
]
```

### 3.2 Get Best Coupon

**Post**/api/coupons/best

Find the best applicable coupon for a given user and cart

**Example Request**

```json
{
  "user": {
    "userId": "u123",
    "userTier": "NEW",
    "country": "IN",
    "lifetimeSpend": 1200,
    "ordersPlaced": 0,
    "previousUsesOfThisCoupon": 0
  },
  "cart": {
    "items": [
      {
        "productId": "p1",
        "category": "electronics",
        "unitPrice": 1500,
        "quantity": 1
      },
      {
        "productId": "p2",
        "category": "fashion",
        "unitPrice": 500,
        "quantity": 2
      }
    ]
  }
}
```

**Example Response**

```Json
{
  "coupon": {
    "code": "WELCOME100",
    "discountType": "FLAT",
    "discountValue": 100
  },
  "cartValue": 2500,
  "discountAmount": 100,
  "finalAmount": 2400
}
```
**If no coupon applies**

```Json

{
  "coupon": null,
  "cartValue": 2500,
  "discountAmount": 0,
  "finalAmount": 2500
}
```

---

## 4. Project Structure

```
src/main/java/com/example/coupon_management
│
├── CouponManagementApplication.java
│
├── controller
│   └── CouponController.java
│
├── service
│   └── CouponService.java
│
├── model
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Coupon.java
│   ├── DiscountType.java
│   ├── Eligibility.java
│   └── UserContext.java
│
└── dto
    ├── BestCouponRequest.java
    └── BestCouponResponse.java
```

---

## 5. Business Logic Summary

**Coupon Validity**

A coupon is valid only if:
```
startDate <= today <= endDate
```

**User Eligibility Check**

- Allowed countries
- Allowed user tiers
- First order only
- Minimum lifetime spend
- Minimum number of order

**Cart Eligibility Checks**

- Minimum cart value
- Minimum item count
- Must contain applicable categories
- Must NOT contain excluded categroies

**Discount Calculation**

- **FLAT**= Fixed discount
- **PERCENT**= (cartValue*discountValue/100)
  - May be capped via maxDiscountAmount

**Best Coupon Tie-Breaker**

If multiple coupons give same discount choose by:

1. Highest discount
2. Earlier end date
3. Alphabetically smaller coupon code

---

## 6. Notes for Reviewer

- All coupons are stored **in memory**, as per assignment requirement.
- Clean separation of layerd (Controller → Service → Model → DTO).
- Fully testable using Postman
- Easily extendable to database usage(JPA/Hibernate0)

---

## 7. Author

Siddhesh Yadav:
Github: https://github.com/ysiddhesh57-ui


    

    
    













