package com.twd.SpringSecurityJWT.repository;


import com.twd.SpringSecurityJWT.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);

}
