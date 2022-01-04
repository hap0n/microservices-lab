package com.footballrent.serviceusers.api;

import com.footballrent.serviceusers.api.dto.UserDTO;
import com.footballrent.serviceusers.repository.model.UserModel;
import com.footballrent.serviceusers.repository.model.UserRole;
import com.footballrent.serviceusers.repository.model.UserStatus;
import com.footballrent.serviceusers.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public final class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public ResponseEntity<List<UserModel>> index() {
        final List<UserModel> users = userServiceImpl.fetchAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserModel> getById(@PathVariable String username) {
        try {
            final UserModel user = userServiceImpl.fetchUserByUsername(username);

            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO user) {
        final String username = user.username();
        final String firstName = user.firstName();
        final String lastName = user.lastName();
        final UserStatus status = user.status();
        final UserRole role = user.role();
        final int age = user.age();
        try {
            final String userId = userServiceImpl.createUser(username, firstName, lastName, status, role, age);
            final String userUri = String.format("/users/%s", userId);

            return ResponseEntity.created(URI.create(userUri)).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/{username}")
    public ResponseEntity<Void> change(@PathVariable String username, @RequestBody UserDTO user) {
        final String firstName = user.firstName();
        final String lastName = user.lastName();
        final UserStatus status = user.status();
        final UserRole role = user.role();
        final int age = user.age();

        try {
            userServiceImpl.updateUser(username, firstName, lastName, status, role, age);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteById(@PathVariable String username) {
        userServiceImpl.deleteUser(username);

        return ResponseEntity.noContent().build();
    }
}
