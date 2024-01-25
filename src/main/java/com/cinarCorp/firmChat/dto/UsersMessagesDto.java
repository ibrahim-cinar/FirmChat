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
public class UsersMessagesDto {
    private String username;
    private List<MessageDto> messages;
}
