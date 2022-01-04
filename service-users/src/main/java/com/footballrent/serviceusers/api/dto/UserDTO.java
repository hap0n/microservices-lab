package com.footballrent.serviceusers.api.dto;

import com.footballrent.serviceusers.repository.model.UserRole;
import com.footballrent.serviceusers.repository.model.UserStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserDTO(
        String username,
        String firstName,
        String lastName,
        UserStatus status,
        UserRole role,
        int age
) { }
