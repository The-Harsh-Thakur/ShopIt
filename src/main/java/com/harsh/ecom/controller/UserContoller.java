package com.harsh.ecom.controller;

import com.harsh.ecom.dto.AddressDTO;
import com.harsh.ecom.dto.UserRequest;
import com.harsh.ecom.dto.UserResponse;
import com.harsh.ecom.model.User;
import com.harsh.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserContoller {

    private final UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest updatedUserRequest)
    {
        boolean updated = userService.updateUser(id, updatedUserRequest);

        if(updated)
            return ResponseEntity.ok("User updated sucessfully");
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
        return ResponseEntity.ok("User Added Successfully");
    }
}
