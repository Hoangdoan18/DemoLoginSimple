package com.example.demologin.model.mapper;

import com.example.demologin.entity.Product;
import com.example.demologin.model.dto.DetailProductInfoDto;


import java.util.ArrayList;

public class ProductMapper {
//    @Autowired
//    private ModelMapper modelMapper;

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
        return detailProductInfoDto;
    }

//    public ProductInfoDto toProductInfoDto(Product product) {
//        ProductInfoDto dto = modelMapper.map(product, ProductInfoDto.class);
//        return dto;
//    }
//
//    public ShortProductInfoDto toShortProductInfoDto(Product product) {
//        ShortProductInfoDto dto = modelMapper.map(product, ShortProductInfoDto.class);
//        return dto;
//    }
}
