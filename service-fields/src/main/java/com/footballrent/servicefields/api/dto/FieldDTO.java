package com.footballrent.servicefields.api.dto;

import java.math.BigDecimal;
import java.time.LocalTime;

public record FieldDTO(
        LocalTime openTime,
        LocalTime closeTime,
        BigDecimal price,
        String name,
        String description
) {
}
