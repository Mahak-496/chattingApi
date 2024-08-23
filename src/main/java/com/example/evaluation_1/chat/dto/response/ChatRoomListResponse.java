package com.example.evaluation_1.chat.dto.response;

import com.example.evaluation_1.chat.dto.response.ChatRoomResponse;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatRoomListResponse {
    private Set<ChatRoomResponse> chatRooms;
}
