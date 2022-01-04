package com.footballrent.servicefields.api;

import com.footballrent.servicefields.api.dto.FieldDTO;
import com.footballrent.servicefields.repository.model.FieldModel;
import com.footballrent.servicefields.service.impl.FieldServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/fields")
@RestController
public final class FieldController {
    private final FieldServiceImpl fieldServiceImpl;

    @GetMapping
    public ResponseEntity<List<FieldModel>> index() {
        final List<FieldModel> fields = fieldServiceImpl.fetchAllFields();

        return ResponseEntity.ok(fields);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldModel> getById(@PathVariable long id) {
        try {
            final FieldModel field = fieldServiceImpl.fetchFieldById(id);

            return ResponseEntity.ok(field);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FieldDTO field) {
        final LocalTime openTime = field.openTime();
        final LocalTime closeTime = field.closeTime();
        final BigDecimal price = field.price();
        final String name = field.name();
        final String description = field.description();

        final long fieldId = fieldServiceImpl.createField(openTime, closeTime, price, name, description);
        final String fieldUri = String.format("/cars/%d", fieldId);

        return ResponseEntity.created(URI.create(fieldUri)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody FieldDTO field) {
        final LocalTime openTime = field.openTime();
        final LocalTime closeTime = field.closeTime();
        final BigDecimal price = field.price();
        final String name = field.name();
        final String description = field.description();

        try {
            fieldServiceImpl.updateField(id, openTime, closeTime, price, name, description);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        fieldServiceImpl.deleteField(id);

        return ResponseEntity.noContent().build();
    }
}
