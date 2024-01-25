package com.cinarCorp.firmChat.service;

import com.cinarCorp.firmChat.exception.UsernameNotFoundException;
import com.cinarCorp.firmChat.model.User;
import com.cinarCorp.firmChat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    protected User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"+username));
    }
    public User getUserByUsername(String username){
        return findByUsername(username);
    }
}
