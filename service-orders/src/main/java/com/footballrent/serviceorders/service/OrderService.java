package com.footballrent.serviceorders.service;

import com.footballrent.serviceorders.repository.model.OrderModel;
import com.footballrent.serviceorders.repository.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface OrderService {
    List<OrderModel> fetchAllOrders();
    OrderModel fetchOrderById(long id) throws IllegalArgumentException;
    long createOrder(
            BigDecimal amount,
            long tenantId,
            long fieldId,
            OrderStatus status,
            LocalDateTime dateTime
    );
    void updateOrder(
            long id,
            BigDecimal amount,
            long tenantId,
            long fieldId,
            OrderStatus status,
            LocalDateTime dateTime
    ) throws IllegalArgumentException;
    void deleteOrder(long id);
}

