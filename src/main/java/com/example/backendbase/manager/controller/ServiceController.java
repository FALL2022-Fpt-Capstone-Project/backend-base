package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;
import com.example.backendbase.manager.entity.request.AddNewGeneralServiceRequest;
import com.example.backendbase.manager.service.GeneralServiceManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager/service")
@RequiredArgsConstructor
public class ServiceController {
    private final GeneralServiceManagerService generalServiceManagerService;

    @GetMapping("/general-service/{contractId}")
    public ResponseEntity<Object> getGeneralServiceByContractId(@PathVariable Long contractId) {
        return ResponseUtils.httpResponse(generalServiceManagerService.getAllGeneralServiceByContractId(contractId));
    }

    @GetMapping("/basic-service")
    public ResponseEntity<Object> getBasicService() {
        return ResponseUtils.httpResponse(generalServiceManagerService.getBasicService());
    }

    @PostMapping("/add-general-service")
    public ResponseEntity<Object> addGeneralServiceByContractId(@RequestBody AddNewGeneralServiceRequest generalService) {
        return ResponseUtils.httpResponse(generalServiceManagerService.addNewGeneralService(generalService));
    }

    @DeleteMapping("/delete-general-service/{generalServiceId}")
    public ResponseEntity<Object> deleteGeneralService(@PathVariable Long generalServiceId) {
        generalServiceManagerService.deleteGeneralService(generalServiceId);
        return ResponseUtils.httpResponse("Xóa thành công");
    }

    @PutMapping("/update-general-service/{generalServiceId}")
    public ResponseEntity<Object> updateGeneralService(@PathVariable Long generalServiceId,
                                                       @RequestBody AddNewGeneralServiceRequest addNewGeneralServiceRequest) {
        return ResponseUtils.httpResponse(generalServiceManagerService.updateGeneralService(generalServiceId, addNewGeneralServiceRequest));
    }
}
