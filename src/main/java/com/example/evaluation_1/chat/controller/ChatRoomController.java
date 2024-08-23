package com.example.evaluation_1.chat.controller;

import com.example.evaluation_1.chat.dto.request.ChatRoomRequest;
import com.example.evaluation_1.chat.dto.response.ChatRoomListResponse;
import com.example.evaluation_1.chat.dto.response.ChatRoomResponse;
import com.example.evaluation_1.chat.service.ChatRoomService;
import com.example.evaluation_1.signupAndLogin.configuration.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatroom")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private JwtService jwtUtil;

    @PostMapping("/createOrJoin")
    public ResponseEntity<ChatRoomResponse> createOrJoinChatRoom(
            @RequestHeader("Authorization") String token,
            @RequestBody ChatRoomRequest request) {

        // Ensure the token is in the correct format
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        try {
            // Extract user ID from the token
            Long userId = jwtUtil.extractUserId(token);

            // Validate the partnerId in the request
            if (request.getPartnerId() == null || userId.equals(request.getPartnerId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            // Create or join the chat room
            ChatRoomResponse response = chatRoomService.createOrJoinChatRoom(userId, request.getPartnerId());
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // Return a 400 Bad Request for illegal arguments
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            // Log the exception and return a 500 Internal Server Error
            // (consider logging the exception details)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ChatRoomListResponse> getChatRooms(
            @RequestHeader("Authorization") String token) {

        // Ensure the token is in the correct format
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        try {
            // Extract user ID from the token
            Long userId = jwtUtil.extractUserId(token);

            // Get chat rooms for the user
            ChatRoomListResponse response = chatRoomService.getChatRooms(userId);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Log the exception and return a 500 Internal Server Error
            // (consider logging the exception details)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
