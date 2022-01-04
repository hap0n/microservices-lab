package com.footballrent.servicefields.service.impl;

import com.footballrent.servicefields.repository.FieldRepository;
import com.footballrent.servicefields.repository.model.FieldModel;
import com.footballrent.servicefields.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;

    public List<FieldModel> fetchAllFields() {
        return fieldRepository.findAll();
    }

    public FieldModel fetchFieldById(long id) throws IllegalArgumentException {
        final Optional<FieldModel> maybeField = fieldRepository.findById(id);

        if (maybeField.isPresent())
            return maybeField.get();
        else
            throw new IllegalArgumentException();
    }

    public long createField(
            LocalTime openTime,
            LocalTime closeTime,
            BigDecimal price,
            String name,
            String description
    ) {
        final FieldModel field = FieldModel.builder()
                .openTime(openTime)
                .closeTime(closeTime)
                .price(price)
                .name(name)
                .description(description)
                .build();

        final FieldModel savedField= fieldRepository.save(field);

        return savedField.getId();
    }

    public void updateField(
            long id,
            LocalTime openTime,
            LocalTime closeTime,
            BigDecimal price,
            String name,
            String description
    ) throws IllegalArgumentException {
        final Optional<FieldModel> maybeField = fieldRepository.findById(id);

        if (maybeField.isEmpty())
            throw new IllegalArgumentException("Invalid field ID");

        final FieldModel field = maybeField.get();

        if(openTime != null) field.setOpenTime(openTime);
        if(closeTime != null) field.setCloseTime(closeTime);
        if(price != null) field.setPrice(price);
        if(name != null && !name.isBlank()) field.setName(name);
        if(description != null && description.isBlank()) field.setDescription(description);

        fieldRepository.save(field);
    }

    public void deleteField(long id) {
        fieldRepository.deleteById(id);
    }

    public boolean isOpenNow(long id) {

        FieldModel field = fetchFieldById(id);

        LocalTime timeNow = LocalTime.now();
        LocalTime openTime = field.getOpenTime();
        LocalTime closeTime = field.getCloseTime();

        return timeNow.isAfter(openTime) && timeNow.isBefore(closeTime);
    }
}
