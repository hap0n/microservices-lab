package com.footballrent.serviceorders.api.dto;

import com.footballrent.serviceorders.repository.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDTO(
    BigDecimal amount,
    long tenantId,
    long fieldId,
    OrderStatus status,
    LocalDateTime dateTime
) { }
