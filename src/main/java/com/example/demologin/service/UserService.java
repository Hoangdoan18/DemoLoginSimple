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
    public User createUser(CreateUserReq req);
    List<User> allInfo();
//    List<UserDto> getListUser();
//    UserDto getUserbyID(int id);
//    UserDto Login(String username, String password);
//    UserDto CreateUser(CreateUserReq user);
//    UserDto UpdateUser(UpdateUserReq user, int id);
//    UserDto UpdatePassword(PasswordUpdateReq user, int id);
//    List<UserDto> DeleteUser(int id);
}
