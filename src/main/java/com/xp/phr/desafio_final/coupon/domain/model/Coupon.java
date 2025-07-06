package com.xp.phr.desafio_final.coupon.domain.model;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Document
public class Coupon {

    @Id
    private final String id;
    @Indexed(unique = true)
    private final String code;
    private final CouponType type;
    private final BigDecimal value;
    private final LocalDate expiryDate;
    @PositiveOrZero
    private Integer maxUsage;
    private long usageCount;
    @Version
    private final Long version;

    public Coupon(
            String code,
            CouponType type,
            BigDecimal value,
            LocalDate expiryDate,
            Integer maxUsage,
            long usageCount,
            Long version
    ) {
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.type = type;
        this.value = value;
        this.expiryDate = expiryDate;
        this.maxUsage = maxUsage;
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
            Integer maxUsage,
            long usageCount,
            Long version
    ) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.value = value;
        this.expiryDate = expiryDate;
        this.maxUsage = maxUsage;
        this.usageCount = usageCount;
        this.version = version;
    }

    public synchronized void incrementUsageCount() {
        this.usageCount++;
    }

    public boolean isPercentType() {
        return this.type.equals(CouponType.PERCENT);
    }

    public boolean isBirthDayType() {
        return this.type.equals(CouponType.BIRTHDAY);
    }

    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }

    public boolean hasNoLimit() {
        return maxUsage == null || maxUsage == 0;
    }

    public boolean usageLimitReached() {
        return maxUsage != null && usageCount >= maxUsage;
    }

}