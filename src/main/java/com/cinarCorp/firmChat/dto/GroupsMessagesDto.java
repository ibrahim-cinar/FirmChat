package com.cinarCorp.firmChat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GroupsMessagesDto {

    private String groupName;
    private String description;
    private String groupImage;
    private String groupAdmin;
    private List<UsersMessagesDto> usersMessagesDto;

}
