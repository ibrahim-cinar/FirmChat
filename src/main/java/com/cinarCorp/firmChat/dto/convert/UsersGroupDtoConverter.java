package com.cinarCorp.firmChat.dto.convert;

import com.cinarCorp.firmChat.dto.UsersGroupDto;
import com.cinarCorp.firmChat.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UsersGroupDtoConverter {
    private final GroupDtoConverter groupDtoConverter;

    public UsersGroupDtoConverter(GroupDtoConverter groupDtoConverter) {
        this.groupDtoConverter = groupDtoConverter;
    }


    public UsersGroupDto convert(User from){
        return new UsersGroupDto(from.getUsername(), from.getEmail(), from.getPhoneNumber(),
                from.getGroups().stream().map(groupDtoConverter::convert).collect(Collectors.toList()));

    }
}
