package com.xp.phr.desafio_final.coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Document
public class Coupon {

    @Id
    private final String id;
    private final String code;
    private final CouponType type;
    private final BigDecimal value;
    private final LocalDate expiryDate;
    private final long usageCount;
    @Version
    private final Long version;

}