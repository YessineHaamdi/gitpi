package com.twd.SpringSecurityJWT.service;


import com.twd.SpringSecurityJWT.entity.Coupon;

import java.util.List;

public interface CouponService {

    Coupon generateCoupon();

    Coupon createCoupon(Coupon coupon);

    Coupon getCouponByCode(String code);

    boolean redeemCoupon(String code);

    List<Coupon> getAllCoupons();
}

