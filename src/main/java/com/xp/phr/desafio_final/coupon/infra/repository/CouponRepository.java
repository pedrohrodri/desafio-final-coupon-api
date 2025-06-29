package com.xp.phr.desafio_final.coupon.infra.repository;

import com.xp.phr.desafio_final.coupon.domain.model.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CouponRepository extends MongoRepository<Coupon, String> {

    boolean existsByCode(String code);

    Optional<Coupon> findByCode(String code);

}