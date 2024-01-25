package com.cinarCorp.firmChat.dto.convert;

import com.cinarCorp.firmChat.dto.MessageDto;
import com.cinarCorp.firmChat.model.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageDtoConverter {


    public MessageDto convert(Message from){

        return new MessageDto(from.getMessageText());

    }
}
