package com.cinarCorp.firmChat.dto.convert;

import com.cinarCorp.firmChat.dto.GroupDto;
import com.cinarCorp.firmChat.model.Group;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GroupDtoConverter {


    public GroupDto convert(Group from){

        return new GroupDto(from.getGroupName(), from.getDescription(), from.getGroupImage(),
                from.getGroupAdmin());
    }
}
