package com.xp.phr.desafio_final.coupon.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CouponUtils {

    public static boolean percentageIsValid(BigDecimal value) {
        return value != null
                && value.compareTo(BigDecimal.ZERO) > 0
                && value.compareTo(BigDecimal.valueOf(100)) <= 0;
    }

    public static boolean isUserBirthDayToday(LocalDate birthDay) {
        if (birthDay == null) {
            return false;
        }

        return birthDay.getMonthValue() == LocalDate.now().getMonthValue() &&
                birthDay.getDayOfMonth() == LocalDate.now().getDayOfMonth();
    }

}
