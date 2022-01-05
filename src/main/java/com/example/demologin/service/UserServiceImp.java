package com.example.demologin.service;

import com.example.demologin.entity.User;
import com.example.demologin.exception.DuplicateRecordException;
import com.example.demologin.exception.NotFoundException;
import com.example.demologin.model.dto.UserDto;
import com.example.demologin.model.mapper.UserMapper;
import com.example.demologin.model.request.CreateUserReq;
import com.example.demologin.model.request.PasswordUpdateReq;
import com.example.demologin.model.request.UpdateUserReq;
import com.example.demologin.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    //List User should be encoding the password
    private static String PassEncoding(String pass){
        return BCrypt.hashpw(pass, BCrypt.gensalt((int) (4 + (Math.random() * 6))));// random log_round
    }

    //Check password
    private boolean CheckPassword(String input, String pass){
        boolean valuate = BCrypt.checkpw(input, pass);
        return valuate;
    }

    @Override
    public User createUser(CreateUserReq req) {
        User user =  userRepository.findByEmail(req.getEmail());

        if(user != null) {
            throw new DuplicateRecordException("Email already exist.");
        }
        user = UserMapper.toUser(req);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> allInfo() {
        return userRepository.findAll();
    }
//
//    @Override
//    public List<UserDto> getListUser() {
//        List<UserDto> result = new ArrayList<>();
//        List<User> users = userRepository.findAll();
//        for (User item:
//                users) {
//            result.add(UserMapper.toUserDto(item));
//        }
//        return result;
//    }
//
//    @Override
//    public UserDto getUserbyID(int id) {
//        User user = userRepository.findById(id);
//        return UserMapper.toUserDto(user);
//    }
//
//    @Override
//    public UserDto Login(String email, String password) {
//        User rs = userRepository.findByEmailAndPassword(email,password);
//        if(rs != null){
//            return UserMapper.toUserDto(rs);
//        }
//        throw new NotFoundException("Username or password is invalid");
//    }
//
//    @Override
//    public UserDto CreateUser(CreateUserReq createUserReq) {
//        //TODO: Create a new user account
//        if(userRepository.findByEmail(createUserReq.getEmail()) != null) {
//            throw new DuplicateRecordException("Email is existed in system");
//        }
//        User user = new User();
//        user.setName(createUserReq.getName());
//        user.setPassword(PassEncoding(createUserReq.getPassword()));
//        user.setEmail(createUserReq.getEmail());
//        user.setBirthday(createUserReq.getBirthday());
//        user.setPhone(createUserReq.getPhone());
//        user.setRole(createUserReq.getRole());
//        user.setAvatar("");
//        userRepository.save(user);
//        return UserMapper.toUserDto(user);
//    }
//
//    @Override
//    public UserDto UpdateUser(UpdateUserReq req, int id) {
//        //TODO: Update information of an user account
//        for (User user1:
//                userRepository.findAll()) {
//            if(user1.getId() == id){
//                if(!req.getEmail().equals(user1.getEmail())){
//                    for (User user2:
//                            userRepository.findAll()) {
//                        //Check email exist
//                        if(req.getEmail().equals(user2.getEmail()))
//                            throw new DuplicateRecordException("New Email already exists in the system");
//                    }
//                    user1.setEmail(req.getEmail());
//                }
//                user1.setName(req.getName());
//                user1.setPhone(req.getPhone());
//                user1.setAvatar(req.getAvatar());
//                return UserMapper.toUserDto(user1);
//            }
//        }
//        throw new NotFoundException("User NOT_FOUND");
//    }

//    @Override
//    public UserDto UpdatePassword(PasswordUpdateReq req, int id) {
//        //TODO: Update password of an account
//        User user = userRepository.findById(id);
//        if((CheckPassword(req.getLast_password(), user.getPassword()))){
//            if(req.getNew_password().equals(req.getConfirm_password())){
//                user.setPassword(PassEncoding(req.getConfirm_password()));
//                return UserMapper.toUserDto(user);
//            }
//            throw new DuplicateRecordException("Confirm-password not match");
//        }
//        throw new DuplicateRecordException("Password not match");

//        for (User user:
//                listuser){
//            if(user.getId() == id){
//                if(CheckPassword(req.getLast_password(), user.getPassword())){
//                    if(req.getNew_password().equals(req.getConfirm_password())){
//                        user.setPassword(PassEncoding(req.getConfirm_password()));
//                        return UserMapper.toUserDto(user);
//                    }
//                    throw new DuplicateRecordException("Confirm-password not match");
//                }
//                throw new DuplicateRecordException("Password not match");
//            }
//        }
//        throw new NotFoundException("User NOT_FOUND");
    }

//    @Override
//    public List<UserDto> DeleteUser(int id) {
////        for (User user:
////                listuser){
////            if(user.getId() == id){
////                listuser.remove(user);
////                return getListUser();
////            }
////        }
////        throw new NotFoundException("User NOT_FOUND");
//        return null;
//    }
//}

