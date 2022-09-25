package com.example.backendbase.user.services;

import com.example.backendbase.user.entity.User;
import com.example.backendbase.user.entity.request.LoginRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;

import java.util.List;

public interface IUserService {

    Object signin(LoginRequest loginRequest);

    Object singup(RegisterRequest registerRequest);

    String logout();

}
