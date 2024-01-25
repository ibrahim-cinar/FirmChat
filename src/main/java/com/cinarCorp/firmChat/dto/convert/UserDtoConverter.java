package com.cinarCorp.firmChat.dto.convert;

import com.cinarCorp.firmChat.dto.UserDto;
import com.cinarCorp.firmChat.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public UserDto convert(User from){
        return new UserDto(from.getUsername(), from.getPassword(), from.getFirstName(),
                from.getLastName(), from.getEmail(),
                from.getPhoneNumber(), from.getAuthorities());
    }

}
