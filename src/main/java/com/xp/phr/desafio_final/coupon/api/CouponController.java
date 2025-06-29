package com.xp.phr.desafio_final.coupon.api;

import com.xp.phr.desafio_final.coupon.api.dto.*;
import com.xp.phr.desafio_final.coupon.api.mapper.CouponMapper;
import com.xp.phr.desafio_final.coupon.domain.exception.EntityNotFoundException;
import com.xp.phr.desafio_final.coupon.domain.service.CouponService;
import com.xp.phr.desafio_final.coupon.domain.service.CouponServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService service;

    public CouponController(CouponServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<CouponResponse> list() {
        return service.findAll().stream()
                .map(CouponMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> getById(@PathVariable String id) {
        return service.findById(id)
                .map(CouponMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CouponResponse> create(@RequestBody @Valid CouponRequest req) {
        return service.save(CouponMapper.toNewEntity(req))
                .map(saved -> ResponseEntity
                        .created(URI.create("/coupons/" + saved.getId()))  // ← URI real
                        .body(CouponMapper.toResponse(saved)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponResponse> update(
            @PathVariable String id,
            @RequestBody @Valid CouponRequest req
    ) {
        return service.update(id, req)
                .map(CouponMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/validate")
    public ValidateCouponResponse validate(@RequestBody Map<String, String> payload) {
        return service.validate(payload.get("code"));
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplyCouponResponse> apply(@RequestBody @Valid ApplyCouponRequest req) {
        return service.apply(req.code(), req.orderTotal(), req.userBirthday())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}/usage")
    public ResponseEntity<UsageDTO> trackUsage(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.trackUsage(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}