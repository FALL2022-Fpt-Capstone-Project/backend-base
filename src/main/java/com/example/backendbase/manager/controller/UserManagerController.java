package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;

import com.example.backendbase.manager.service.AssistantAccountManagerService;
import com.example.backendbase.manager.entity.request.ChangePassRequest;
import com.example.backendbase.manager.entity.request.ModifyAssistantAccountRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/manager/user")
@RestController
public class UserManagerController {

    @Autowired
    private AssistantAccountManagerService assistantAccManager;

    @GetMapping("/list-assistant-account")
    public ResponseEntity<Object> getListAccount() {
        return ResponseUtils.httpResponse(assistantAccManager.getListAssistantAccount());
    }

    @PostMapping("/add-assistant-account")
    public ResponseEntity<Object> addAssistantAccount(@RequestBody RegisterRequest registerRequest) {
        return ResponseUtils.httpResponse(assistantAccManager.addNewAssistantAccount(registerRequest));
    }

    @PutMapping("/update-account")
    public ResponseEntity<Object> updateAccount(@RequestBody ModifyAssistantAccountRequest request) {
        return ResponseUtils.httpResponse(assistantAccManager.updateAccount(request));
    }

    @PutMapping("/deactive-assistant-account/id/{id}")
    public ResponseEntity<Object> disableAssistantAccount(@PathVariable(name = "id") Long id) {
        return ResponseUtils.httpResponse(assistantAccManager.deactiveAssistantAccount(id));
    }

    @PutMapping("/deactive-assistant-account/username/{username}")
    public ResponseEntity<Object> disableAssistantAccount(@PathVariable(name = "username") String username) {
        return ResponseUtils.httpResponse(assistantAccManager.deactiveAssistantAccount(username));
    }

    @PutMapping("/change-password-account/id/{id}")
    public ResponseEntity<Object> changePasswordAccount(@PathVariable(name = "id") Long id, @RequestBody ChangePassRequest request) {
        return ResponseUtils.httpResponse(assistantAccManager.changePassword(id, request));
    }

    @PutMapping("/change-password-account/username/{username}")
    public ResponseEntity<Object> changePasswordAccount(@PathVariable(name = "username") String userName, @RequestBody ChangePassRequest request) {
        return ResponseUtils.httpResponse(assistantAccManager.changePassword(userName, request));
    }

}
