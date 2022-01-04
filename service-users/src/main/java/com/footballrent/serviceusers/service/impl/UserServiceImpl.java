package com.footballrent.serviceusers.service.impl;

import com.footballrent.serviceusers.repository.UserRepository;
import com.footballrent.serviceusers.repository.model.UserModel;
import com.footballrent.serviceusers.repository.model.UserRole;
import com.footballrent.serviceusers.repository.model.UserStatus;
import com.footballrent.serviceusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public List<UserModel> fetchAllUsers() {
        return userRepository.findAll();
    }

    public UserModel fetchUserByUsername(String username) throws IllegalArgumentException {
        final Optional<UserModel> maybeUser = userRepository.findById(username);

        if (maybeUser.isPresent())
            return maybeUser.get();
        else
            throw new IllegalArgumentException();
    }

    public String createUser(
            String username,
            String firstName,
            String lastName,
            UserStatus status,
            UserRole role,
            int age
    ) {
        final UserModel user = UserModel.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .status(status)
                .role(role)
                .age(age)
                .build();

        final UserModel savedUser = userRepository.save(user);

        return savedUser.getUsername();
    }

    public void updateUser(
            String username,
            String firstName,
            String lastName,
            UserStatus status,
            UserRole role,
            int age
    ) throws IllegalArgumentException {
        final Optional<UserModel> maybeUser = userRepository.findById(username);

        if (maybeUser.isEmpty())
            throw new IllegalArgumentException("Invalid username");

        final UserModel user = maybeUser.get();

        if(firstName != null && !firstName.isBlank()) user.setFirstName(firstName);
        if(lastName != null && !lastName.isBlank()) user.setLastName(lastName);
        if(status != null) user.setStatus(status);
        if(role != null) user.setRole(role);
        if(age > 0 && age < 120) user.setAge(age);

        userRepository.save(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }
}
