package com.footballrent.serviceorders.api;

import com.footballrent.serviceorders.api.dto.OrderDTO;
import com.footballrent.serviceorders.repository.model.OrderModel;
import com.footballrent.serviceorders.repository.model.OrderStatus;
import com.footballrent.serviceorders.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public final class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    @GetMapping
    public ResponseEntity<List<OrderModel>> index() {
        final List<OrderModel> orders = orderServiceImpl.fetchAllOrders();

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderModel> getById(@PathVariable long id) {
        try {
            final OrderModel order = orderServiceImpl.fetchOrderById(id);

            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody OrderDTO order) {
        final BigDecimal amount = order.amount();
        final long tenantId = order.tenantId();
        final long fieldId = order.fieldId();
        final OrderStatus status = order.status();
        final LocalDateTime dateTime = order.dateTime();

        final long orderId = orderServiceImpl.createOrder(amount, tenantId, fieldId, status, dateTime);
        final String orderUri = String.format("/cars/%d", orderId);

        return ResponseEntity.created(URI.create(orderUri)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody OrderDTO order) {
        final BigDecimal amount = order.amount();
        final long tenantId = order.tenantId();
        final long fieldId = order.fieldId();
        final OrderStatus status = order.status();
        final LocalDateTime dateTime = order.dateTime();

        try {
            orderServiceImpl.updateOrder(id, amount, tenantId, fieldId, status, dateTime);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        orderServiceImpl.deleteOrder(id);

        return ResponseEntity.noContent().build();
    }
}
