package com.cinarCorp.firmChat.service;

import com.cinarCorp.firmChat.dto.Request.CreateUserRequest;
import com.cinarCorp.firmChat.dto.UserDto;
import com.cinarCorp.firmChat.dto.convert.UserDtoConverter;
import com.cinarCorp.firmChat.exception.*;
import com.cinarCorp.firmChat.model.User;
import com.cinarCorp.firmChat.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    protected Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public UserDto getUserByUsername(String username) {
        var username1 = findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User could not find by username " + username));

        return userDtoConverter.convert(username1);
    }
    protected Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
    public UserDto getUserByEmail(String email) {
        var userMail = findUserByEmail(email).orElseThrow(() -> new EmailNotFoundException("User could not find by email " + email));
        return userDtoConverter.convert(userMail);
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
    protected boolean isEmailUnique(String email) {
        Optional<User> existingUserEmail = userRepository.findUserByEmail(email);
        return existingUserEmail.isEmpty();
    }
    protected boolean isUsernameUnique(String username) {
        Optional<User> existingUserUsername = userRepository.findUserByUsername(username);
        return existingUserUsername.isEmpty();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        return  user.orElseThrow(EntityNotFoundException::new);
    }
    private static boolean isInputValid(CreateUserRequest request) {
        return request.getUsername() != null &&
                request.getFirstName() != null &&
                request.getLastName() != null &&
                request.getEmail() != null &&
                request.getPassword() != null &&
                request.getAuthorities() != null;
    }
    public User createUser(CreateUserRequest request) {
        return new User(request.getUsername(),bCryptPasswordEncoder.encode(request.getPassword()),
                request.getFirstName(),request.getLastName(),
                request.getEmail(),request.getPhoneNumber(),
                true,true,true,
                true,true,request.getAuthorities());
    }
    public User createUserFromRequest(CreateUserRequest request) {
        if(!isInputValid(request)){
            throw new InvalidInputException("Invalid input");
        }
        if(!isEmailUnique(request.getEmail())){
            throw new EmailAlreadyExistException("Email already exists");
        }
        if(!isUsernameUnique(request.getUsername())){
            throw new UsernameAlreadyExistException("Username already exists");
        }
        if(!patternMatches(request.getEmail(),"^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
            throw new InvalidInputException("Email is not valid");
        }
        if(!patternMatches(request.getPhoneNumber(),"\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")){
            throw new InvalidInputException("Phone number is not valid");
        }
        return createUser(request);
    }
    public UserDto createNewUser(CreateUserRequest createUserRequest) {
        User user = createUserFromRequest(createUserRequest);
        userRepository.save(user);
        return userDtoConverter.convert(user);

    }
}
