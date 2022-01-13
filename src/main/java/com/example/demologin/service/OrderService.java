package com.example.demologin.service;

import com.example.demologin.entity.User;
import com.example.demologin.model.dto.OrderInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public List<OrderInfoDto> getListOrderWaitingforProduct(User user);
    public List<OrderInfoDto> getListOrderInShipping(User user);
    public List<OrderInfoDto> getListOrderShipped(User user);
    public List<OrderInfoDto> getListOrderReturned(User user);
    public List<OrderInfoDto> getListOrderCancelled(User user);
}
