package com.example.demologin.service.impl;

import com.example.demologin.entity.Product;
import com.example.demologin.entity.User;
import com.example.demologin.model.dto.CategoryInfo;
import com.example.demologin.model.request.CreateProductReq;
import com.example.demologin.repository.ProductRepository;
import com.example.demologin.repository.UserRepository;
import com.example.demologin.service.AdminService;
import com.example.demologin.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImp implements AdminService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void CreateProduct(CreateProductReq req) {
        Product product = modelMapper.map(req, Product.class);

        productService.addProduct(product);
    }

    @Override
    public void UpdateUserStatus(User user, boolean status) {
        user.setStatus(status);
        userRepository.save(user);
    }


}
