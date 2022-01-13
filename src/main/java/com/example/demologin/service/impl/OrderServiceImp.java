package com.example.demologin.service.impl;

import com.example.demologin.entity.User;
import com.example.demologin.model.dto.OrderInfoDto;
import com.example.demologin.repository.OrderRepository;
import com.example.demologin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderInfoDto> getListOrderWaitingforProduct(User user) {
        return orderRepository.getListOrderOfPersonByStatus(1 , user.getId());
    }

    @Override
    public List<OrderInfoDto> getListOrderInShipping(User user) {
        return orderRepository.getListOrderOfPersonByStatus(2 , user.getId());
    }

    @Override
    public List<OrderInfoDto> getListOrderShipped(User user) {
        return orderRepository.getListOrderOfPersonByStatus(3 , user.getId());
    }

    @Override
    public List<OrderInfoDto> getListOrderReturned(User user) {
        return orderRepository.getListOrderOfPersonByStatus(4 , user.getId());
    }

    @Override
    public List<OrderInfoDto> getListOrderCancelled(User user) {
        return orderRepository.getListOrderOfPersonByStatus(5 , user.getId());
    }


}
