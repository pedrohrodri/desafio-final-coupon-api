package com.xp.phr.desafio_final.coupon.domain.service;

import com.xp.phr.desafio_final.coupon.api.dto.ApplyCouponResponse;
import com.xp.phr.desafio_final.coupon.api.dto.CouponRequest;
import com.xp.phr.desafio_final.coupon.api.dto.UsageDTO;
import com.xp.phr.desafio_final.coupon.api.dto.ValidateCouponResponse;
import com.xp.phr.desafio_final.coupon.domain.model.Coupon;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CouponService {

    boolean existsByCode(String code);

    Optional<Coupon> findById(String id);

    List<Coupon> findAll();

    Optional<Coupon> save(Coupon coupon);

    boolean deleteById(String id);

    Optional<Coupon> update(String id, CouponRequest req);

    ValidateCouponResponse validate(String code);

    Optional<ApplyCouponResponse> apply(String code, BigDecimal orderTotal);

    Optional<ApplyCouponResponse> apply(String code, BigDecimal orderTotal, LocalDate userBirthday);

    UsageDTO trackUsage(String id);

}
