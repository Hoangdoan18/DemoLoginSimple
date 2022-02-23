package com.example.demologin.service;

import com.example.demologin.entity.Product;
import com.example.demologin.entity.ProductSize;
import com.example.demologin.model.dto.DetailProductInfoDto;
import com.example.demologin.model.dto.ProductInfoDto;
import com.example.demologin.model.dto.ShortProductInfoDto;
import com.example.demologin.model.request.CreateProductReq;
import com.example.demologin.model.request.FilterProductReq;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public interface ProductService {
    Product addProduct(Product product);

    List<ProductInfoDto> getListNewProducts();

    List<ProductInfoDto> getListBestSellerProduct();

    List<ProductInfoDto> getListSuggestProduct();

    DetailProductInfoDto getProductDetailByID(String id);

    List<ProductInfoDto> getRelatedProducts(String id);

    List<Integer> getListAvailableSize(String id);

//    Pageable searchProductByKeyword(String keyword, Integer page);

    List<ProductSize> getListSizeOfProduct(String id);

    List<ShortProductInfoDto> getAllProduct();

    List<ShortProductInfoDto> getAvailableProducts();

    List<ProductInfoDto> filterProduct(FilterProductReq req);

//    List<Product> searchByCategory(String category);
}
