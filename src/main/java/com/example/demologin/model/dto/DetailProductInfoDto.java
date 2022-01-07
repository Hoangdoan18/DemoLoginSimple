package com.example.demologin.model.dto;

import com.example.demologin.entity.Brand;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailProductInfoDto {
    private String id;

    private String name;

    private String slug;

    private long price;

    private int totalSold;

    private ArrayList<String> productImages;

    private ArrayList<String> onfeetImages;

    private long promotionPrice;

    private String couponCode;

    private String description;

    private Brand brand;
}
