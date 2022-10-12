package com.example.backendbase.user.services;

import com.example.backendbase.security.enums.ERole;
import com.example.backendbase.manager.entity.request.ChangePassRequest;
import com.example.backendbase.manager.entity.response.AddAssistantAccountResponse;
import com.example.backendbase.manager.entity.response.ChangeAssistantPassResponse;
import com.example.backendbase.manager.entity.response.ListAssistantAccountResponse;
import com.example.backendbase.user.entity.request.LoginResponse;
import com.example.backendbase.user.entity.User;
import com.example.backendbase.manager.entity.request.ModifyAssistantAccountRequest;
import com.example.backendbase.user.entity.request.LoginRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;

import java.util.List;

public interface IUserService {

    LoginResponse signin(LoginRequest loginRequest);

    User singup(RegisterRequest registerRequest);

    String logout();

    User updateAccount(ModifyAssistantAccountRequest changeRequest);

    List<ListAssistantAccountResponse> getListUserByRole(ERole role);

    List<ListAssistantAccountResponse> getListAssistantAccount();

    String deactiveAssistantAccount(Object value);

    ChangeAssistantPassResponse changePassword(Object value, ChangePassRequest changePassRequest);

    AddAssistantAccountResponse addNewAssistantAccount(RegisterRequest registerRequest);
}
