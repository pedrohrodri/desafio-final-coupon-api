package com.xp.phr.desafio_final.coupon;

public final class CouponMapper {
    public static Coupon toNewEntity(CouponRequest req) {
        return new Coupon(
                req.code(),
                req.type(),
                req.value(),
                req.expiryDate(),
                0,
                null
        );
    }
}