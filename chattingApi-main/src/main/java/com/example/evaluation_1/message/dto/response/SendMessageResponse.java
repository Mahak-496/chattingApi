package com.example.evaluation_1.message.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Data
@Builder
public class SendMessageResponse {
    private String status;
    private Long roomId;
    private MessageData messageData;
}
