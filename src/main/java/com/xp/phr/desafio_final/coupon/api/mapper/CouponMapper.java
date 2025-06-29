package com.xp.phr.desafio_final.coupon.api.mapper;

import com.xp.phr.desafio_final.coupon.domain.model.Coupon;
import com.xp.phr.desafio_final.coupon.api.dto.CouponRequest;
import com.xp.phr.desafio_final.coupon.api.dto.CouponResponse;

public final class CouponMapper {
    public static Coupon toNewEntity(CouponRequest req) {
        return new Coupon(
                req.code(),
                req.type(),
                req.value(),
                req.expiryDate(),
                req.maxUsage(),
                0,
                null
        );
    }

    public static Coupon toUpdatedEntity(
            Coupon existingValuesCoupon,
            CouponRequest newValuesCoupon
    ) {
        return new Coupon(
                existingValuesCoupon.getId(),
                existingValuesCoupon.getCode(),
                newValuesCoupon.type(),
                newValuesCoupon.value(),
                newValuesCoupon.expiryDate(),
                newValuesCoupon.maxUsage(),
                existingValuesCoupon.getUsageCount(),
                existingValuesCoupon.getVersion()
        );
    }

    public static CouponResponse toResponse(Coupon c) {
        return new CouponResponse(
                c.getId(),
                c.getCode(),
                c.getType(),
                c.getValue(),
                c.getMaxUsage(),
                c.getUsageCount(),
                c.getExpiryDate()
        );
    }
}