package com.example.backendbase.manager.service;

import com.example.backendbase.security.enums.ERole;
import com.example.backendbase.user.entity.User;
import com.example.backendbase.manager.entity.request.ChangePassRequest;
import com.example.backendbase.manager.entity.request.ModifyAssistantAccountRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;
import com.example.backendbase.manager.entity.response.AddAssistantAccountResponse;
import com.example.backendbase.manager.entity.response.ChangeAssistantPassResponse;
import com.example.backendbase.manager.entity.response.ListAssistantAccountResponse;

import java.util.List;

public interface AssistantAccountManagerService {

    User updateAccount(ModifyAssistantAccountRequest changeRequest);

    List<ListAssistantAccountResponse> getListUserByRole(ERole role);

    List<ListAssistantAccountResponse> getListAssistantAccount();

    String deactiveAssistantAccount(Object value);

    ChangeAssistantPassResponse changePassword(Object value, ChangePassRequest changePassRequest);

    AddAssistantAccountResponse addNewAssistantAccount(RegisterRequest registerRequest);
}
