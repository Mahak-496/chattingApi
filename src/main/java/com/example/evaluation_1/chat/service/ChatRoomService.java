package com.example.evaluation_1.chat.service;


import com.example.evaluation_1.chat.dto.mapper.ChatRoomMapper;
import com.example.evaluation_1.chat.dto.response.ChatRoomListResponse;
import com.example.evaluation_1.chat.dto.response.ChatRoomResponse;
import com.example.evaluation_1.chat.entity.ChatRoom;
import com.example.evaluation_1.chat.repository.ChatRoomRepository;
import com.example.evaluation_1.signupAndLogin.entity.User;
import com.example.evaluation_1.signupAndLogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserRepository userRepository;

    public ChatRoomResponse createOrJoinChatRoom(Long userId, Long partnerId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User partner = userRepository.findById(partnerId).orElseThrow(() -> new RuntimeException("Partner not found"));

        Set<User> users = new HashSet<>();
        users.add(user);
        users.add(partner);

        if (users.size() != 2) {
            throw new IllegalArgumentException("Chat room must have exactly two users.");
        }

        Optional<ChatRoom> existingRoom = chatRoomRepository.findByUsers(users);
        if (existingRoom.isPresent()) {
            return ChatRoomMapper.toResponse(existingRoom.get(), userId);
        }

        ChatRoom newRoom = new ChatRoom();
        newRoom.setUsers(users);
        chatRoomRepository.save(newRoom);

        return ChatRoomMapper.toResponse(newRoom, userId);
    }

    public ChatRoomListResponse getChatRooms(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Set<ChatRoom> chatRooms = chatRoomRepository.findByUsersContaining(user);

        Set<ChatRoomResponse> chatRoomResponses = chatRooms.stream()
                .filter(room -> room.getUsers().size() == 2)
                .map(room -> ChatRoomMapper.toResponse(room, userId))
                .collect(Collectors.toSet());

        return new ChatRoomListResponse(chatRoomResponses);
    }
}
