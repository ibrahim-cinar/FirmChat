package com.cinarCorp.firmChat.dto.convert;

import com.cinarCorp.firmChat.dto.UsersMessagesDto;
import com.cinarCorp.firmChat.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UsersMessagesDtoConverter {

    private final MessageDtoConverter messageDtoConverter;

    public UsersMessagesDtoConverter(MessageDtoConverter messageDtoConverter) {
        this.messageDtoConverter = messageDtoConverter;
    }

    public UsersMessagesDto convert(User from){

        return new UsersMessagesDto(from.getUsername(),
                from.getMessages().stream().map(messageDtoConverter::convert).collect(Collectors.toList()));
    }
}