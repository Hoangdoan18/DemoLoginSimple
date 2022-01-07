package com.example.demologin.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckPromotion {
    private int discountType;

    private long discountValue;

    private long maximumDiscountValue;
}
