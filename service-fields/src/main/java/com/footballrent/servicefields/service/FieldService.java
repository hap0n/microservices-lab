package com.footballrent.servicefields.service;

import com.footballrent.servicefields.repository.model.FieldModel;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

public interface FieldService {
    List<FieldModel> fetchAllFields();
    FieldModel fetchFieldById(long id) throws IllegalArgumentException;
    long createField(
            LocalTime openTime,
            LocalTime closeTime,
            BigDecimal price,
            String name,
            String description
    );
    void updateField(
            long id,
            LocalTime openTime,
            LocalTime closeTime,
            BigDecimal price,
            String name,
            String description
    ) throws IllegalArgumentException;
    void deleteField(long id);
    boolean isOpenNow(long id);
}

