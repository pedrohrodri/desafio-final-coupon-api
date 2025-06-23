package com.xp.phr.desafio_final.coupon;

import java.util.UUID;

public final class CouponMapper {
    public static Coupon toNewEntity(CouponRequest req) {
        return new Coupon(
                UUID.randomUUID().toString(),
                req.code(),
                req.type(),
                req.value(),
                req.expiryDate(),
                0,
                null
        );
    }
}