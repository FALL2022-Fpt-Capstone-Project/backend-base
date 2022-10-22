package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;

import com.example.backendbase.manager.service.StaffManagerService;
import com.example.backendbase.manager.entity.request.ChangePassRequest;
import com.example.backendbase.manager.entity.request.ModifyAccountRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/manager/account")
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class StaffController {
    private final StaffManagerService assistantAccManager;

    @GetMapping("/list-staff-account")
    public ResponseEntity<Object> getListAccountByFilter(@RequestParam(name = "order", required = false, defaultValue = "latest") String order,
                                                 @RequestParam(name = "role", required = false, defaultValue = "all") String roles,
                                                 @RequestParam(name = "permission", required = false, defaultValue = "1,2,3,4,5") String permission,
                                                 @RequestParam(name = "startDate", required = false) String startDate,
                                                 @RequestParam(name = "endDate", required = false) String endDate,
                                                 @RequestParam(name = "deactivate", required = false, defaultValue = "-1") String deactivate) {
        return ResponseUtils.httpResponse(assistantAccManager.getListAssistantAccount(order, roles, Integer.parseInt(deactivate), permission, startDate, endDate));
    }

    @GetMapping("/list-all-staff-account")
    public ResponseEntity<Object> getListAccount() {
        return ResponseUtils.httpResponse(assistantAccManager.getAllListStaffAccount());
    }

    @GetMapping("/staff-account/{accountId}")
    public ResponseEntity<Object> getAccountById(@PathVariable(name = "accountId") Long id) {
        return ResponseUtils.httpResponse(assistantAccManager.getStaffAccountById(id));
    }

    @PostMapping("/add-staff-account")
    public ResponseEntity<Object> addAssistantAccount(@RequestBody RegisterRequest registerRequest) {
        return ResponseUtils.httpResponse(assistantAccManager.addNewAssistantAccount(registerRequest));
    }

    @PutMapping("/update-account/{staffId}")
    public ResponseEntity<Object> updateAccount(@RequestBody ModifyAccountRequest request,
                                                @PathVariable(name = "staffId") Long staffId) {
        return ResponseUtils.httpResponse(assistantAccManager.updateAccount(request, staffId));
    }

    @PutMapping("/change-pass-account/{id}")
    public ResponseEntity<Object> changePasswordAccount(@PathVariable(name = "id") Long id, @RequestBody ChangePassRequest request) {
        return ResponseUtils.httpResponse(assistantAccManager.changePassword(id, request));
    }
}
