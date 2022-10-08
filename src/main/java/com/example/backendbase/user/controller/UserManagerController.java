package com.example.backendbase.user.controller;

import com.example.backendbase.common.utils.ResponseUtils;

import com.example.backendbase.user.entity.request.ChangePassRequest;
import com.example.backendbase.user.entity.request.ChangeRoleRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;
import com.example.backendbase.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/manager/user")
@RestController
public class UserManagerController {

    @Autowired
    private IUserService userService;

    @GetMapping("/list-assistant-account")
    public ResponseEntity<Object> getListAccount() {
        return ResponseUtils.httpResponse(userService.getListAssistantAccount());
    }

    @PostMapping("/add-assistant-account")
    public ResponseEntity<Object> addAssistantAccount(@RequestBody RegisterRequest registerRequest) {
        return ResponseUtils.httpResponse(userService.addNewAssistantAccount(registerRequest));
    }

    @PutMapping("/update-role-account")
    public ResponseEntity<Object> updateAccountRole(@RequestBody ChangeRoleRequest request) {
        return ResponseUtils.httpResponse(userService.changeRole(request));
    }

    @PutMapping("/deactive-assistant-account/id/{id}")
    public ResponseEntity<Object> disableAssistantAccount(@PathVariable(name = "id") Long id) {
        return ResponseUtils.httpResponse(userService.deactiveAssistantAccount(id));
    }

    @PutMapping("/deactive-assistant-account/username/{username}")
    public ResponseEntity<Object> disableAssistantAccount(@PathVariable(name = "username") String username) {
        return ResponseUtils.httpResponse(userService.deactiveAssistantAccount(username));
    }

    @PutMapping("/change-password-account/id/{id}")
    public ResponseEntity<Object> changePasswordAccount(@PathVariable(name = "id") Long id, @RequestBody ChangePassRequest request) {
        return ResponseUtils.httpResponse(userService.changePassword(id, request));
    }

    @PutMapping("/change-password-account/username/{username}")
    public ResponseEntity<Object> changePasswordAccount(@PathVariable(name = "username") String userName, @RequestBody ChangePassRequest request) {
        return ResponseUtils.httpResponse(userService.changePassword(userName, request));
    }

}
