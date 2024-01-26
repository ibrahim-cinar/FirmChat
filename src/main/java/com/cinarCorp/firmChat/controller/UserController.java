package com.cinarCorp.firmChat.controller;

import com.cinarCorp.firmChat.dto.Request.CreateUserRequest;
import com.cinarCorp.firmChat.dto.UserDto;
import com.cinarCorp.firmChat.model.User;
import com.cinarCorp.firmChat.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping//make private api
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());

    }
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));

    }
    @PostMapping("/newUser")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.createNewUser(createUserRequest));
    }
}
