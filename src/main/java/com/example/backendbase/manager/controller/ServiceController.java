package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;
import com.example.backendbase.manager.service.GeneralServiceManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager/service")
@RequiredArgsConstructor
public class ServiceController {
    private final GeneralServiceManagerService generalServiceManagerService;

    @GetMapping("/general-service/{contractId}")
    public ResponseEntity<Object> getGeneralServiceByContractId(@PathVariable Long contractId){
        return ResponseUtils.httpResponse(generalServiceManagerService.getAllGeneralServiceByContractId(contractId));
    }
}
