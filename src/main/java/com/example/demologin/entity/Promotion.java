package com.example.demologin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "coupon_code",unique = true, nullable = false)
    private String couponCode;

    @Column(name = "create_at")
    private Timestamp createAt;

    @Column(name = "discount_type")
    private int discountType;

    @Column(name = "discount_value")
    private long discountValue;

    @Column(name = "expired_at")
    private Timestamp expiredAt;

    @Column(name = "is_active", columnDefinition = "BOOLEAN")
    private boolean isActive;

    @Column(name = "is_public", columnDefinition = "BOOLEAN")
    private boolean isPublic;

    @Column(name = "maximum_discount_value")
    private long maximumDiscountValue;

    @Column(name = "name", length = 300, nullable = false)
    private String name;

    //Mapping with UsedPromotion in Order table
    public Promotion(Order.UsedPromotion promotion) {
        this.discountValue = promotion.getDiscountValue();
        this.maximumDiscountValue = promotion.getMaximumDiscountValue();
        this.discountType = promotion.getDiscountType();
        this.couponCode = promotion.getCouponCode();
    }
}
