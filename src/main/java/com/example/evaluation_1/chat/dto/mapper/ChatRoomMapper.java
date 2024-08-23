package com.example.evaluation_1.chat.dto.mapper;

import com.example.evaluation_1.chat.dto.response.ChatRoomResponse;
import com.example.evaluation_1.chat.entity.ChatRoom;
import com.example.evaluation_1.signupAndLogin.entity.User;

public class ChatRoomMapper {

    public static ChatRoomResponse toResponse(ChatRoom chatRoom, Long userId) {
        User partner = chatRoom.getUsers().stream()
                .filter(user -> user.getId()!=(userId))
                .findFirst()
                .orElse(null);

        if (partner == null) {
            return null;
        }

        ChatRoomResponse response = new ChatRoomResponse();
        response.setRoomId(chatRoom.getId());
        response.setPartnerName(partner.getFirstName() + " " + partner.getLastName());
        response.setPartnerProfilePicture(partner.getProfilePictureUrl());

        return response;
    }
}
