package com.example.demologin.service;

import com.example.demologin.entity.User;
import com.example.demologin.model.dto.UserDto;
import com.example.demologin.model.request.CreateUserReq;
import com.example.demologin.model.request.PasswordUpdateReq;
import com.example.demologin.model.request.UpdateUserReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createUser(CreateUserReq req);
    UserDto UpdateUser(UpdateUserReq req, User user);
    String UpdatePassword(PasswordUpdateReq req, User user);
}
