package com.example.backendbase.manager.service;

import com.example.backendbase.user.entity.User;
import com.example.backendbase.manager.entity.request.ChangePassRequest;
import com.example.backendbase.manager.entity.request.ModifyAssistantAccountRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;
import com.example.backendbase.manager.entity.response.AddAssistantAccountResponse;
import com.example.backendbase.manager.entity.response.ChangeAssistantPassResponse;
import com.example.backendbase.manager.entity.response.ListAssistantAccountResponse;

import java.util.List;

public interface StaffManagerService {

    User updateAccount(ModifyAssistantAccountRequest changeRequest, Long staffId);

    List<ListAssistantAccountResponse> getListAssistantAccount(String condition, String roles, int filter);

    String deactiveAssistantAccount(Object value);

    ChangeAssistantPassResponse changePassword(Object value, ChangePassRequest changePassRequest);

    AddAssistantAccountResponse addNewAssistantAccount(RegisterRequest registerRequest);
}
