package com.example.backendbase.user.controller;

import com.example.backendbase.common.utils.ResponseUtils;
import com.example.backendbase.user.entity.User;
import com.example.backendbase.user.entity.request.LoginRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;
import com.example.backendbase.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    IUserService userService;

    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody LoginRequest loginRequest){
        return ResponseUtils.httpResponse(userService.signin(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody RegisterRequest registerRequest){
        return ResponseUtils.httpResponse(userService.singup(registerRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@RequestBody RegisterRequest registerRequest){
        return ResponseUtils.httpResponse(userService.logout());
    }
}
