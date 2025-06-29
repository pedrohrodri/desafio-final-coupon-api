package com.xp.phr.desafio_final.coupon.api.dto;

import com.xp.phr.desafio_final.coupon.domain.model.CouponType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CouponRequest(
        @NotBlank String code,
        @NotNull CouponType type,
        @Positive BigDecimal value,
        @PositiveOrZero Integer maxUsage,
        @Future LocalDate expiryDate
) {
}