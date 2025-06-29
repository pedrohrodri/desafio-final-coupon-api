package com.xp.phr.desafio_final.coupon.api.dto;

import java.math.BigDecimal;

public record ApplyCouponResponse(
        BigDecimal original,
        BigDecimal discount,
        BigDecimal finalTotal
) {
}