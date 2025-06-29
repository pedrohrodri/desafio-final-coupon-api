package com.xp.phr.desafio_final.coupon.api.dto;

import com.xp.phr.desafio_final.coupon.domain.model.CouponType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CouponResponse(
        String id,
        String code,
        CouponType type,
        BigDecimal value,
        Integer maxUsage,
        long usageCount,
        LocalDate expiryDate
) {
}