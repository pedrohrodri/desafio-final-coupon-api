package com.xp.phr.desafio_final.coupon;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CouponRequest(
        @NotBlank String code,
        @NotNull CouponType type,
        @Positive BigDecimal value,
        @Future LocalDate expiryDate
) {
}