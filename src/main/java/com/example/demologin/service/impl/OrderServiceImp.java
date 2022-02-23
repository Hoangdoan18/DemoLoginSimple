package com.example.demologin.service.impl;

import com.example.demologin.entity.Order;
import com.example.demologin.entity.ProductSize;
import com.example.demologin.entity.ProductSizeId;
import com.example.demologin.entity.User;
import com.example.demologin.exception.BadRequestException;
import com.example.demologin.model.dto.OrderInfoDto;
import com.example.demologin.repository.OrderRepository;
import com.example.demologin.repository.ProductSizeRepository;
import com.example.demologin.security.user.CustomUserDetails;
import com.example.demologin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.demologin.config.Constant.*;
@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductSizeRepository productSizeRepository;


    @Override
    public List<OrderInfoDto> getListOrderWaitingforProduct(User user) {
        return orderRepository.getListOrderOfPersonByStatus( ORDER_STATUS , user.getId());
    }

    @Override
    public List<OrderInfoDto> getListOrderInShipping(User user) {
        return orderRepository.getListOrderOfPersonByStatus( DELIVERY_STATUS , user.getId());
    }

    @Override
    public List<OrderInfoDto> getListOrderShipped(User user) {
        return orderRepository.getListOrderOfPersonByStatus( COMPLETE_STATUS , user.getId());
    }

    @Override
    public List<OrderInfoDto> getListOrderReturned(User user) {
        return orderRepository.getListOrderOfPersonByStatus( RETURNED_STATUS , user.getId());
    }

    @Override
    public List<OrderInfoDto> getListOrderCanceled(User user) {
        return orderRepository.getListOrderOfPersonByStatus( CANCELED_STATUS , user.getId());
    }

    @Override
    public Order getOrderbyId(long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }

    @Override
    public void cancelOrder(long orderid) {
        Order order = orderRepository.getById(orderid);

        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        if(user.getId() != order.getBuyer().getId()) {
            throw new BadRequestException("User not have this order.");
        }
        if(order.getStatus() != ORDER_STATUS) {
            throw new BadRequestException("Order status invalid to cancel.");
        }
        // Plus 1 quantity for canceled order
        order.setStatus( CANCELED_STATUS );
        order.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        order.setModifiedBy(user);
        ProductSize productSize = productSizeRepository.getById(new ProductSizeId(order.getProductId().getId(), order.getSize()));
        productSize.setQuantity(productSize.getQuantity() + 1);
        order.setNote("Order cancelled by user at " + new Date());
        productSizeRepository.save(productSize);
        orderRepository.save(order);
    }
}
