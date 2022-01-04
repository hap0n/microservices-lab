package com.footballrent.serviceorders.repository;

import com.footballrent.serviceorders.repository.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
