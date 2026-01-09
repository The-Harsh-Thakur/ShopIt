package com.harsh.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserContoller {

    private final UserService userService;

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.fetchUser(id);
    }

    @PostMapping("/api/users")
    public String createUser(@RequestBody User user) {
        userService.addUser(user);
        return "User Added Successfully";
    }
}
