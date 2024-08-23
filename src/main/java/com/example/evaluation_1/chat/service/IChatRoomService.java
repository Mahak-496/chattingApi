package com.example.evaluation_1.chat.service;


import com.example.evaluation_1.chat.dto.request.ChatRoomRequest;
import com.example.evaluation_1.chat.dto.response.ChatRoomListResponse;
import com.example.evaluation_1.chat.dto.response.ChatRoomResponse;

public interface IChatRoomService {
    ChatRoomResponse createChatRoom(ChatRoomRequest requestDTO);

    ChatRoomResponse getChatRoom(Long id);

    ChatRoomListResponse getAllChatRooms();
}
