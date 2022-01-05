package com.example.demologin.model.mapper;


import com.example.demologin.entity.User;
import com.example.demologin.model.dto.UserDto;
import com.example.demologin.model.request.CreateUserReq;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class UserMapper {
    public static UserDto toUserDto(User user){
        UserDto udto = new UserDto();
        udto.setId(user.getId());
        udto.setName(user.getFullname());
        udto.setEmail(user.getEmail());
        udto.setPhone(user.getPhone());
        udto.setAvatar(user.getAvatar());
        return udto;
    }

    public static User toUser(CreateUserReq req){
        User user = new User();
        user.setFullname(req.getFullname());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        // Hash password using BCrypt
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        user.setCreateAt(new Timestamp(System.currentTimeMillis()));
        user.setStatus(true);
        user.setRole(new ArrayList<String>(Arrays.asList("USER")));

        return user;
    }
}