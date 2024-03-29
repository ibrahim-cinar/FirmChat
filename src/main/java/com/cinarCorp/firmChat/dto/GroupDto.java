package com.cinarCorp.firmChat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDto {

    private String groupName;
    private String description;
    private String groupImage;
    private String groupAdmin;

}
