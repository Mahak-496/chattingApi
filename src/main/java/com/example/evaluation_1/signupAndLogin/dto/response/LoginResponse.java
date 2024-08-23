package com.example.evaluation_1.signupAndLogin.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dob;
    private String profilePictureUrl;
}
