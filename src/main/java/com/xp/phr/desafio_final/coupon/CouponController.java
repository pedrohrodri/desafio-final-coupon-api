package com.xp.phr.desafio_final.coupon;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponRepository repo;

    public CouponController(CouponRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Coupon> create(@RequestBody @Valid CouponRequest req) {
        return Optional.of(req)
                .filter(it -> repo.existsByCode(it.code()))
                .map(it -> repo.save(CouponMapper.toNewEntity(it)))
                .map(it -> ResponseEntity.created(null).body(it))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public List<Coupon> list() {
        return repo.findAll();
    }
}