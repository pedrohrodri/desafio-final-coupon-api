package com.xp.phr.desafio_final.coupon.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ApplyCouponRequest(String code, BigDecimal orderTotal, LocalDate userBirthday) {
}
