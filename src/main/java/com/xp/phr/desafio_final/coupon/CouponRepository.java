package com.xp.phr.desafio_final.coupon;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CouponRepository extends MongoRepository<Coupon, String> {
    boolean existsByCode(String code);
}