package com.example.evaluation_1.signupAndLogin.dto.mapper;

import com.example.evaluation_1.signupAndLogin.dto.request.LoginRequest;
import com.example.evaluation_1.signupAndLogin.dto.request.RegistrationRequest;
import com.example.evaluation_1.signupAndLogin.dto.response.LoginResponse;
import com.example.evaluation_1.signupAndLogin.dto.response.RegistrationResponse;
import com.example.evaluation_1.signupAndLogin.entity.User;

public class UserMapper {
    public static User toUserEntity(RegistrationRequest registrationRequest) {
        return User.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .password(registrationRequest.getPassword())
                .phoneNumber(registrationRequest.getPhoneNumber())
                .dob(registrationRequest.getDob())
                .build();
    }

    public static RegistrationResponse toRegistrationResponse(User user) {
        return RegistrationResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .dob(user.getDob())
                .profilePictureUrl(user.getProfilePictureUrl())
                .build();
    }

    public static User toUserEntityForLogin(LoginRequest loginRequest) {
        return User.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();
    }

    public static LoginResponse toLoginResponse(User user, String token) {
        return LoginResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .dob(user.getDob())
                .profilePictureUrl(user.getProfilePictureUrl())
                .build();
    }
}
