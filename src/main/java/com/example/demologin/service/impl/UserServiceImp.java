package com.example.demologin.service.impl;

import com.example.demologin.entity.User;
import com.example.demologin.exception.DuplicateRecordException;
import com.example.demologin.exception.NotFoundException;
import com.example.demologin.model.dto.UserDto;
import com.example.demologin.model.mapper.UserMapper;
import com.example.demologin.model.request.CreateUserReq;
import com.example.demologin.model.request.PasswordUpdateReq;
import com.example.demologin.model.request.UpdateUserReq;
import com.example.demologin.repository.UserRepository;
import com.example.demologin.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    //List User should be encoding the password
    private static String PassEncoding(String pass) {
        // random log_round
        return BCrypt.hashpw(pass, BCrypt.gensalt((int) (4 + (Math.random() * 6))));
    }

    //Check password
    private boolean CheckPassword(String input, String pass) {
        boolean valuate = BCrypt.checkpw(input, pass);
        return valuate;
    }

    //TODO: Create an user account
    @Override
    public User createUser(CreateUserReq req) {
        User user = userRepository.findByEmail(req.getEmail());

        if (user != null) {
            throw new DuplicateRecordException("Email already exist.");
        }
        user = UserMapper.toUser(req);
        userRepository.save(user);
        return user;
    }


    @Override
    public UserDto UpdateUser(UpdateUserReq req, User user) {
        //TODO: Update information (EXCEPT PASSWORD) of an user account
        user.setFullname(req.getName());
        user.setEmail(req.getEmail());
        user.setAvatar(req.getAvatar());
        user.setPhone(req.getPhone());
        user.setAddress(req.getAddress());
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public String UpdatePassword(PasswordUpdateReq req, User user) {
        //TODO: Update password of an account
        if (CheckPassword(req.getLast_password(), user.getPassword())) {
            if (req.getNew_password().equals(req.getConfirm_password())) {
                user.setPassword(PassEncoding(req.getNew_password()));
                userRepository.save(user);
                return "Update password success.";
            } else {
                throw new DuplicateRecordException("Password confirm not match.");
            }
        } else throw new DuplicateRecordException("Password wrong.");
    }
}