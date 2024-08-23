package com.example.evaluation_1.signupAndLogin.controller;

import com.example.evaluation_1.signupAndLogin.dto.request.LoginRequest;
import com.example.evaluation_1.signupAndLogin.dto.request.RegistrationRequest;
import com.example.evaluation_1.signupAndLogin.dto.response.LoginResponse;
import com.example.evaluation_1.signupAndLogin.dto.response.RegistrationResponse;
import com.example.evaluation_1.signupAndLogin.service.UserService;
import com.example.evaluation_1.signupAndLogin.utils.ApiResponse;
import com.example.evaluation_1.signupAndLogin.utils.ResponseSender;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("api/auth/register")
    public ResponseEntity<Object> registerUser(
            @Valid @ModelAttribute RegistrationRequest registrationRequest,
            @RequestParam("profileImage") MultipartFile profileImage) {
        try {
            RegistrationResponse response = userService.registerUser(registrationRequest, profileImage);
            String token = userService.generateToken(registrationRequest.getEmail());

            ApiResponse apiResponse = ApiResponse.builder()
                    .message("User registered successfully")
                    .data(response)
                    .token(token)
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

    @PostMapping("api/auth/login")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = userService.loginUser(loginRequest);
            String token = userService.generateToken(loginRequest.getEmail());
            ApiResponse apiResponse = ApiResponse.builder()
                    .message("User logged in successfully")
                    .data(response)
                    .token(token)
                    .statusCode(HttpStatus.OK.value())
                    .build();
            return ResponseSender.send(apiResponse);
        } catch (AuthenticationException e) {
            ApiResponse apiResponse = ApiResponse.builder()
                    .message(e.getMessage())
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
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
