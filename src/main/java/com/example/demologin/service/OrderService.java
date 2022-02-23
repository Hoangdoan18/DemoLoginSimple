package com.example.demologin.service;

import com.example.demologin.entity.Order;
import com.example.demologin.entity.User;
import com.example.demologin.model.dto.OrderInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    List<OrderInfoDto> getListOrderWaitingforProduct(User user);
    List<OrderInfoDto> getListOrderInShipping(User user);
    List<OrderInfoDto> getListOrderShipped(User user);
    List<OrderInfoDto> getListOrderReturned(User user);
    List<OrderInfoDto> getListOrderCanceled(User user);
    Order getOrderbyId(long id);
    void cancelOrder(long orderid);
}
