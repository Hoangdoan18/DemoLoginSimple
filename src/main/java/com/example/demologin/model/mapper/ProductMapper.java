package com.example.demologin.model.mapper;

import com.example.demologin.entity.Product;
import com.example.demologin.model.dto.DetailProductInfoDto;
import com.example.demologin.model.dto.ProductInfoDto;

import java.util.ArrayList;

public class ProductMapper {
    public static DetailProductInfoDto toDetailProductInfoDto(Product product) {
        DetailProductInfoDto detailProductInfoDto = new DetailProductInfoDto();
        detailProductInfoDto.setId(product.getId());
        detailProductInfoDto.setName(product.getName());
        detailProductInfoDto.setSlug(product.getId());
        detailProductInfoDto.setPrice(product.getPrice());
        detailProductInfoDto.setTotalSold(product.getTotalSold());
        detailProductInfoDto.setProductImages((ArrayList<String>) product.getProductImages());
        detailProductInfoDto.setOnfeetImages((ArrayList<String>) product.getOnfeetImages());
//        detailProductInfoDto.setCouponCode();
        detailProductInfoDto.setDescription(product.getDescription());
        detailProductInfoDto.setBrand(product.getBrandId());
        return null;
    }
}
