package com.footballrent.serviceusers.service;

import com.footballrent.serviceusers.repository.model.UserModel;
import com.footballrent.serviceusers.repository.model.UserRole;
import com.footballrent.serviceusers.repository.model.UserStatus;

import java.util.List;

public interface UserService {
    List<UserModel> fetchAllUsers();
    UserModel fetchUserByUsername(String username) throws IllegalArgumentException;
    String createUser(
            String username,
            String firstName,
            String lastName,
            UserStatus status,
            UserRole role,
            int age
    );
    void updateUser(
            String username,
            String firstName,
            String lastName,
            UserStatus status,
            UserRole role,
            int age
    ) throws IllegalArgumentException;
    void deleteUser(String username);
}

