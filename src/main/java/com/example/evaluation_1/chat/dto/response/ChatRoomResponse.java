package com.example.evaluation_1.chat.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomResponse {
    private Long roomId;
    private String partnerName;
    private String partnerProfilePicture;
}
