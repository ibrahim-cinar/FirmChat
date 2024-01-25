package com.cinarCorp.firmChat.dto.convert;

import com.cinarCorp.firmChat.dto.GroupsMessagesDto;
import com.cinarCorp.firmChat.model.Group;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GroupsMessagesDtoConverter {
    private final UsersMessagesDtoConverter usersMessagesDtoConverter;

    public GroupsMessagesDtoConverter(UsersMessagesDtoConverter usersMessagesDtoConverter) {
        this.usersMessagesDtoConverter = usersMessagesDtoConverter;
    }

    public GroupsMessagesDto convert(Group from){
        return new GroupsMessagesDto(from.getGroupName(), from.getDescription(), from.getGroupImage(), from.getGroupAdmin(),
                from.getUsers().stream().map(usersMessagesDtoConverter::convert).collect(Collectors.toList()));
    }
}
