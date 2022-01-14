package com.example.demologin.controller;

import com.example.demologin.entity.User;
import com.example.demologin.model.dto.OrderInfoDto;
import com.example.demologin.model.mapper.UserMapper;
import com.example.demologin.model.request.PasswordUpdateReq;
import com.example.demologin.model.request.UpdateUserReq;
import com.example.demologin.repository.UserRepository;
import com.example.demologin.security.user.CustomUserDetails;
import com.example.demologin.service.OrderService;
import com.example.demologin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public OrderService orderService;

    @GetMapping("/api/profile")
    public ResponseEntity<?> getUserInfo(){
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @PostMapping("/api/profile/update-profile")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UpdateUserReq req){
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        userService.UpdateUser(req, user);
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    @PostMapping("/api/profile/update-password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody PasswordUpdateReq req) {
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        userService.UpdatePassword(req, user);
        return ResponseEntity.ok("Update success.");
    }

    @GetMapping("/api/account/order-history")
    public String getListOrdered(Model model){
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        List<OrderInfoDto> in_waiting = orderService.getListOrderWaitingforProduct(user);
        model.addAttribute("in-waiting", in_waiting);

        List<OrderInfoDto> in_shipping = orderService.getListOrderInShipping(user);
        model.addAttribute("in-shipping", in_shipping);

        List<OrderInfoDto> shipped = orderService.getListOrderShipped(user);
        model.addAttribute("shipped", shipped);

        List<OrderInfoDto> returned = orderService.getListOrderReturned(user);
        model.addAttribute("returned", returned);

        List<OrderInfoDto> canceled = orderService.getListOrderCanceled(user);
        model.addAttribute("canceled", canceled);

        return "buying-order";
    }

    @PostMapping("api/cancel-order")
    public String canceltheOrder() {

        return null;
    }
}
