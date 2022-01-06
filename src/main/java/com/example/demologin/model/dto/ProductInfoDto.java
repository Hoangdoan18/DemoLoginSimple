package com.example.demologin.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductInfoDto {
    private String id;

    private String name;

    private String slug;

    private long price;

    private int totalSold;

    private String image;

    private int promotionPrice;

    public ProductInfoDto(String id, String name, String slug, long price, int totalSold, String image) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.price = price;
        this.totalSold = totalSold;
        this.image = image;
    }
}
