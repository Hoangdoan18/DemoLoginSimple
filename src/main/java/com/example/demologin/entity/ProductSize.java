package com.example.demologin.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ProductSizeId.class)
@Table(name = "product_size")
public class ProductSize {
    @Id
    @Column(name = "product_id")
    private String productId;

    @Id
    @Column(name = "size")
    private int size;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
