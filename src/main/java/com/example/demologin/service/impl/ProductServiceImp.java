package com.example.demologin.service.impl;

import com.example.demologin.entity.Configuration;
import com.example.demologin.entity.Product;
import com.example.demologin.entity.ProductSize;
import com.example.demologin.exception.BadRequestException;
import com.example.demologin.exception.NotFoundException;
import com.example.demologin.model.dto.DetailProductInfoDto;
import com.example.demologin.model.dto.ProductInfoDto;
import com.example.demologin.model.dto.ShortProductInfoDto;
import com.example.demologin.model.mapper.ProductMapper;
import com.example.demologin.model.request.FilterProductReq;
import com.example.demologin.repository.*;
import com.example.demologin.service.ProductService;
import com.example.demologin.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /*******************************/
    @Override
    public Product addProduct(Product product){
        return null;
    }

    @Override
    public List<ProductInfoDto> getListNewProducts() {
        return productRepository.getListNewProduct(5);
    }

    @Override
    public List<ProductInfoDto> getListBestSellerProduct() {
        return productRepository.getListBestSellerProduct(5);
    }



    @Override
    public List<ProductInfoDto> getListSuggestProduct() {
        List<Configuration> configs = configurationRepository.findAll();
        Configuration config = configs.get(0);
        List<ProductInfoDto> product = productRepository.getListSuggestProduct(config.getObo_choices(), 5);
        return product;
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

    @Override
    public List<Integer> getListAvailableSize(String id) {
        return productSizeRepository.findAllSizeOfProduct(id);
    }

    public Pageable searchProductByKeyword(String keyword, Integer page) {
        if (keyword == null) keyword = "";

        if (page == null)  page = 1;

        int limit = 15;

        return null;
    }

    @Override
    public List<ProductSize> getListSizeOfProduct(String id) {
        return productSizeRepository.findByProductId(id);
    }

    @Override
    public List<ShortProductInfoDto> getAllProduct() {
        return productRepository.getAllProduct();
    }

    @Override
    public List<ShortProductInfoDto> getAvailableProducts() {
        return productRepository.getAvailableProducts();
    }

    @Override
    public List<ProductInfoDto> filterProduct(FilterProductReq req) {
        int limit = 8;
        if (req.getBrands() == null) {
            req.setBrands(brandRepository.getAllBrandName());
        }
        if (req.getCategories() == null) {
            req.setCategories(categoryRepository.getAllCategoryName());
        }
        if(req.getSize() == null) {
            req.setSize(productSizeRepository.getAllSize());
        }
        if (req.getMaxPrice() == null) {
            req.setMaxPrice(Long.MAX_VALUE);
        }
        if (req.getMinPrice() == null) {
            req.setMinPrice(0l);
        }

        PageUtil pageUtil = new PageUtil(limit, req.getPage());
        int totalItems = productRepository.countProductBySize(req.getBrands(),req.getCategories(),
                req.getMinPrice(), req.getMaxPrice(), req.getSize());
        if(totalItems == 0) {
            throw new BadRequestException("List filter product is empty.");
        }

        if(req.getPage() > pageUtil.totalPage(totalItems)) {
            throw new BadRequestException("Page requested is higher than total page.");
        }

        List<ProductInfoDto> products = productRepository.searchProductBySize(req.getBrands(),req.getCategories(),
                req.getMinPrice(), req.getMaxPrice(), req.getSize(),limit, pageUtil.offset());

        return products;
    }

//    @Override
//    public List<Product> searchByCategory(String brandName) {
//        long brand_id = productRepository.searchByBranName(brandName);
//        List<Product> list = productRepository.searchProductByBrandId(brand_id);
//        return list;
//    }
}
