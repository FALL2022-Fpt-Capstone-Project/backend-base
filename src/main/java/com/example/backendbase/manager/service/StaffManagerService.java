package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.request.ChangePassRequest;
import com.example.backendbase.manager.entity.request.ModifyAccountRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;
import com.example.backendbase.manager.entity.response.AddAssistantAccountResponse;
import com.example.backendbase.manager.entity.response.ChangeAssistantPassResponse;
import com.example.backendbase.manager.entity.response.StaffAccountResponse;

import java.util.List;

public interface StaffManagerService {

    StaffAccountResponse updateAccount(ModifyAccountRequest changeRequest, Long staffId);

    List<StaffAccountResponse> getListAssistantAccount(String condition, String roles, int filter, String permission, String startDate, String endDate);

    ChangeAssistantPassResponse changePassword(Object value, ChangePassRequest changePassRequest);

    AddAssistantAccountResponse addNewAssistantAccount(RegisterRequest registerRequest);

    List<StaffAccountResponse> getAllListStaffAccount();

    StaffAccountResponse getStaffAccountById(Long id);
}
