package com.example.evaluation_1.chat.dto.response;

import com.example.evaluation_1.chat.dto.response.ChatRoomResponse;
import lombok.*;

import java.util.Set;

//   DTO for providing a summary of a user's chat rooms.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatRoomListResponse {
    private String receiverName;
    private String profileImage;
}
