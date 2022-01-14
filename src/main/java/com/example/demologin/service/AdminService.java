package com.example.demologin.service;

import com.example.demologin.entity.User;
import com.example.demologin.model.request.CreateProductReq;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    void CreateProduct(CreateProductReq req);

    void UpdateUserStatus(User user, boolean status);
}
