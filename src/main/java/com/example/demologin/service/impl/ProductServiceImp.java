package com.example.demologin.service.impl;

import com.example.demologin.entity.Configuration;
import com.example.demologin.entity.Product;
import com.example.demologin.exception.NotFoundException;
import com.example.demologin.model.dto.DetailProductInfoDto;
import com.example.demologin.model.dto.ProductInfoDto;
import com.example.demologin.model.mapper.ProductMapper;
import com.example.demologin.repository.ConfigurationRepository;
import com.example.demologin.repository.ProductRepository;
import com.example.demologin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Override
    public Product addProduct(Product product){
        return null;
    }

    @Override
    public List<ProductInfoDto> getListNewProduct() {
        return productRepository.getListNewProduct(5);
    }

    @Override
    public List<ProductInfoDto> getListBestSellerProduct() {
        return productRepository.getListBestSellerProduct(5);
    }



    @Override
    public List<ProductInfoDto> getListSuggestProduct() {
        List<Configuration> configs = configurationRepository.findAll();
        if(configs.size() > 0){
            Configuration config = configs.get(0);
            List<ProductInfoDto> product = productRepository.getListSuggestProduct(config.getObo_choices(), 5);
            return product;
        }
        return null;
    }

    @Override
    public DetailProductInfoDto getProductDetailByID(String id) {
        Optional<Product> result = productRepository.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException("Product doesn't exist.");
        }

        Product product = result.get();

        if (!product.isAvailable()) {
            throw new NotFoundException("Product doesn't exist.");
        }
        DetailProductInfoDto dto = ProductMapper.toDetailProductInfoDto(product);


        return dto;
    }

    @Override
    public List<ProductInfoDto> getRelatedProducts(String id) {
        Optional<Product> result = productRepository.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException("Product doesn't exist.");
        }

        List<ProductInfoDto> products = productRepository.getRelatedProducts(id, 5);
        return products;
    }
}
