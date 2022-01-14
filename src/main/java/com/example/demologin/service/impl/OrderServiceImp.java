package com.example.demologin.service.impl;

import com.example.demologin.entity.Order;
import com.example.demologin.entity.ProductSize;
import com.example.demologin.entity.ProductSizeId;
import com.example.demologin.entity.User;
import com.example.demologin.model.dto.OrderInfoDto;
import com.example.demologin.repository.OrderRepository;
import com.example.demologin.repository.ProductSizeRepository;
import com.example.demologin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductSizeRepository productSizeRepository;


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
    public List<OrderInfoDto> getListOrderCanceled(User user) {
        return orderRepository.getListOrderOfPersonByStatus(5 , user.getId());
    }

    @Override
    public void cancelOrder(long orderid, User user) {
        Order order = orderRepository.getById(orderid);
        order.setStatus(5);
        order.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        order.setModifiedBy(user);
        order.setNote("Order cancelled by user at " + new Date());

        // Plus 1 quantity for canceled order
        ProductSize productSize = productSizeRepository.getById(new ProductSizeId(order.getProductId().getId(), order.getSize()));
        productSize.setQuantity(productSize.getQuantity() + 1);
        productSizeRepository.save(productSize);
        orderRepository.save(order);
    }
}
