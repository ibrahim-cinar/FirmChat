package com.cinarCorp.firmChat.controller;

import com.cinarCorp.firmChat.dto.Request.AuthRequest;
import com.cinarCorp.firmChat.dto.Request.CreateUserRequest;
import com.cinarCorp.firmChat.dto.UserDto;
import com.cinarCorp.firmChat.exception.UsernameNotFoundException;
import com.cinarCorp.firmChat.model.User;
import com.cinarCorp.firmChat.service.JwtService;
import com.cinarCorp.firmChat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }
    @PostMapping("/newUser")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.createNewUser(createUserRequest));
    }
    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }
        log.info("Invalid username {}" +authRequest.getUsername());
        throw new UsernameNotFoundException("Invalid username {}" +authRequest.getUsername());
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
