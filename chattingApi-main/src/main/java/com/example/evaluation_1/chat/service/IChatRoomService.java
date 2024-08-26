package com.example.evaluation_1.chat.service;


import com.example.evaluation_1.chat.dto.request.ChatRoomRequest;
import com.example.evaluation_1.chat.dto.response.ChatRoomListResponse;
import com.example.evaluation_1.chat.dto.response.ChatRoomResponse;

import java.util.List;

public interface IChatRoomService {
    ChatRoomResponse createChatRoom(ChatRoomRequest request, String user1Email);

    List<ChatRoomListResponse> getAllChatRooms(String userEmail);

    String getUserEmailFromToken(String token);


}
