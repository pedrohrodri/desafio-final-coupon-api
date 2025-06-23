package com.xp.phr.desafio_final.coupon;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
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

    public Coupon(
            String code,
            CouponType type,
            BigDecimal value,
            LocalDate expiryDate,
            long usageCount,
            Long version
    ) {
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.type = type;
        this.value = value;
        this.expiryDate = expiryDate;
        this.usageCount = usageCount;
        this.version = version;
    }

    @PersistenceCreator
    public Coupon(
            String id,
            String code,
            CouponType type,
            BigDecimal value,
            LocalDate expiryDate,
            long usageCount,
            Long version
    ) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.value = value;
        this.expiryDate = expiryDate;
        this.usageCount = usageCount;
        this.version = version;
    }
}