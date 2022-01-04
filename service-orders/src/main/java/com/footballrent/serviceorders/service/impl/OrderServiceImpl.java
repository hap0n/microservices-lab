package com.footballrent.serviceorders.service.impl;

import com.footballrent.serviceorders.repository.OrderRepository;
import com.footballrent.serviceorders.repository.model.OrderModel;
import com.footballrent.serviceorders.repository.model.OrderStatus;
import com.footballrent.serviceorders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public List<OrderModel> fetchAllOrders() {
        return orderRepository.findAll();
    }

    public OrderModel fetchOrderById(long id) throws IllegalArgumentException {
        final Optional<OrderModel> maybeOrder = orderRepository.findById(id);

        if (maybeOrder.isPresent())
            return maybeOrder.get();
        else
            throw new IllegalArgumentException();
    }

    public long createOrder(
            BigDecimal amount,
            long tenantId,
            long fieldId,
            OrderStatus status,
            LocalDateTime dateTime
    ) {
        final OrderModel order = OrderModel.builder()
                .amount(amount)
                .tenantId(tenantId)
                .fieldId(fieldId)
                .status(status)
                .dateTime(dateTime)
                .build();

        final OrderModel savedOrder= orderRepository.save(order);

        return savedOrder.getId();
    }

    public void updateOrder(
            long id,
            BigDecimal amount,
            long tenantId,
            long orderId,
            OrderStatus status,
            LocalDateTime dateTime
    ) throws IllegalArgumentException {
        final Optional<OrderModel> maybeOrder = orderRepository.findById(id);

        if (maybeOrder.isEmpty())
            throw new IllegalArgumentException("Invalid order ID");

        final OrderModel order = maybeOrder.get();
        
        if(amount != null) order.setAmount(amount);
        if(tenantId > 0) order.setTenantId(tenantId);
        if(orderId > 0) order.setFieldId(orderId);
        if(status != null) order.setStatus(status);
        if(dateTime != null) order.setDateTime(dateTime);

        orderRepository.save(order);
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }
}
