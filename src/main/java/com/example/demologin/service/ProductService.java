package com.example.demologin.service;

import com.example.demologin.entity.Product;
import com.example.demologin.model.dto.DetailProductInfoDto;
import com.example.demologin.model.dto.ProductInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product addProduct(Product product);

    List<ProductInfoDto> getListNewProduct();

    List<ProductInfoDto> getListBestSellerProduct();

    List<ProductInfoDto> getListSuggestProduct();

    DetailProductInfoDto getProductDetailByID(String id);

    List<ProductInfoDto> getRelatedProducts(String id);
}
