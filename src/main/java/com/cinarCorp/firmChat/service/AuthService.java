package com.cinarCorp.firmChat.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Value("${jwt.key}")
    private String SECRET;
    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return null;
    }
}
