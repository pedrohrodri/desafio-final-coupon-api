package com.xp.phr.desafio_final.coupon.domain.service;

import com.xp.phr.desafio_final.coupon.api.dto.ApplyCouponResponse;
import com.xp.phr.desafio_final.coupon.api.dto.CouponRequest;
import com.xp.phr.desafio_final.coupon.api.dto.UsageDTO;
import com.xp.phr.desafio_final.coupon.api.dto.ValidateCouponResponse;
import com.xp.phr.desafio_final.coupon.api.mapper.CouponMapper;
import com.xp.phr.desafio_final.coupon.domain.exception.EntityNotFoundException;
import com.xp.phr.desafio_final.coupon.domain.model.Coupon;
import com.xp.phr.desafio_final.coupon.domain.model.CouponType;
import com.xp.phr.desafio_final.coupon.domain.model.CouponUtils;
import com.xp.phr.desafio_final.coupon.infra.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    private static final Logger log = LoggerFactory.getLogger(CouponServiceImpl.class);

    private final CouponRepository repository;

    public CouponServiceImpl(CouponRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByCode(String code) {
        return repository.existsByCode(code);
    }

    @Transactional(readOnly = true)
    public List<Coupon> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Coupon> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Coupon> save(Coupon coupon) {
        if (existsByCode(coupon.getCode())) {
            log.warn("Tentativa de criar cupom duplicado: {}", coupon.getCode());
            return Optional.empty();
        }

        if (coupon.isPercentType() && !CouponUtils.percentageIsValid(coupon.getValue())) {
            log.warn("Percentual inválido para cupom {}: {}", coupon.getCode(), coupon.getValue());
            return Optional.empty();
        }

        return Optional.of(repository.save(coupon));
    }

    @Transactional
    public boolean deleteById(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    @Transactional
    public Optional<Coupon> update(String id, CouponRequest req) {
        return repository.findById(id)
                .map(it -> CouponMapper.toUpdatedEntity(it, req))
                .map(repository::save);
    }

    public ValidateCouponResponse validate(String code) {
        return repository.findByCode(code)
                .map(c -> {
                    if (c.getExpiryDate() == null) {
                        return new ValidateCouponResponse(true, "É válido.");
                    }

                    boolean valid = !c.getExpiryDate().isBefore(LocalDate.now());

                    String reason = valid ? null : "Expirado";

                    return new ValidateCouponResponse(valid, reason);
                })
                .orElseGet(() -> new ValidateCouponResponse(false, "Não encontrado."));
    }

    @Transactional
    public Optional<ApplyCouponResponse> apply(
            String code,
            BigDecimal orderTotal
    ) {
        return apply(code, orderTotal, null);
    }

    @Transactional
    @Override
    public Optional<ApplyCouponResponse> apply(
            String code,
            BigDecimal orderTotal,
            LocalDate userBirthday
    ) {
        try {
            return repository.findByCode(code)
                    .filter(it -> !it.isExpired())
                    .filter(it -> it.hasNoLimit() || !it.usageLimitReached())
                    .filter(it -> !it.isBirthDayType() || CouponUtils.isUserBirthDayToday(userBirthday))
                    .map(it -> {
                        BigDecimal discount = it.getType() == CouponType.PERCENT
                                ? orderTotal.multiply(it.getValue().divide(BigDecimal.valueOf(100)))
                                : it.getValue().min(orderTotal);

                        BigDecimal finalTotal = orderTotal.subtract(discount);
                        it.incrementUsageCount();
                        repository.save(it);

                        log.info("Cupom {} aplicado. desconto={} totalFinal={}", code, discount, finalTotal);

                        return new ApplyCouponResponse(orderTotal, discount, finalTotal);
                    });
        } catch (org.springframework.dao.OptimisticLockingFailureException ex) {
            log.warn("Concorrência detectada ao aplicar cupom {} – tente novamente", code);
            return Optional.empty();
        }
    }

    public UsageDTO trackUsage(String id) {
        return repository.findById(id)
                .map(c -> new UsageDTO(c.getUsageCount()))
                .orElseThrow(() -> new EntityNotFoundException("Cupom não encontrado."));
    }
}
