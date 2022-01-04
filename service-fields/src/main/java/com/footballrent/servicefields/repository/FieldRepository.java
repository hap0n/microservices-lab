package com.footballrent.servicefields.repository;

import com.footballrent.servicefields.repository.model.FieldModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<FieldModel, Long> {
}
