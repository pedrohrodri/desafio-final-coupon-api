package com.xp.phr.desafio_final.coupon.api.dto;

public record ValidateCouponResponse(
        boolean valid,
        String reason
) {
}