package com.footballrent.serviceusers.repository;

import com.footballrent.serviceusers.repository.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, String> {
}
