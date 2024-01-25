package com.cinarCorp.firmChat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersGroupDto {
    private String username;
    private String email;
    private String phoneNumber;
    private List<GroupDto> groups;
}
