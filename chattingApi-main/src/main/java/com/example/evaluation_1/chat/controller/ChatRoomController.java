package com.example.evaluation_1.chat.controller;


import com.example.evaluation_1.chat.dto.request.ChatRoomRequest;
import com.example.evaluation_1.chat.dto.response.ChatRoomListResponse;
import com.example.evaluation_1.chat.dto.response.ChatRoomResponse;
import com.example.evaluation_1.chat.service.ChatRoomService;
import com.example.evaluation_1.signupAndLogin.utils.ApiResponse;
import com.example.evaluation_1.signupAndLogin.utils.ResponseSender;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

//Endpoint to create a new chat room or return an existing one.
    @PostMapping("/create")
    public ResponseEntity<Object> createChatRoom(
            @RequestBody ChatRoomRequest request,
            HttpServletRequest httpServletRequest) {

        // Extract token from header and get user email from token
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
        }

        String userEmail = chatRoomService.getUserEmailFromToken(token);

        try {
            ChatRoomResponse response = chatRoomService.createChatRoom(request, userEmail);
            String message = response.getChatRoomId() != null ? "Chat room already exists" : "Chat room created successfully";
            ApiResponse apiResponse = ApiResponse.builder()
                    .message(message)
                    .data(response)
                    .statusCode(HttpStatus.CREATED.value())
                    .build();
            return ResponseSender.send(apiResponse);
        } catch (Exception e) {
            ApiResponse apiResponse = ApiResponse.builder()
                    .message(e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
            return ResponseSender.send(apiResponse);
        }
    }


   // Endpoint to retrieve all chat rooms for the authenticated user.
    @GetMapping("/get")
    public ResponseEntity<Object> getAllChatRooms(HttpServletRequest httpServletRequest) {

        // Extract token from header and get user email from token
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
        }

        String userEmail = chatRoomService.getUserEmailFromToken(token);

        try {
            List<ChatRoomListResponse> chatRooms = chatRoomService.getAllChatRooms(userEmail);
            ApiResponse apiResponse = ApiResponse.builder()
                    .message("List of chat rooms retrieved successfully")
                    .data(chatRooms)
                    .statusCode(HttpStatus.OK.value())
                    .build();
            return ResponseSender.send(apiResponse);
        } catch (Exception e) {
            ApiResponse apiResponse = ApiResponse.builder()
                    .message(e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
            return ResponseSender.send(apiResponse);
        }
    }
}
