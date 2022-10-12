package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;
import com.example.backendbase.manager.entity.request.AddContractRequest;
import com.example.backendbase.manager.exception.ManagerException;
import com.example.backendbase.manager.service.ContractManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager/contract")
@RequiredArgsConstructor
public class ContractController {
    private final ContractManagerService contractManagerService;

    @PostMapping("/add-new-contract")
    public ResponseEntity<Object> addNewContract(@RequestBody AddContractRequest request) throws ManagerException {
        return ResponseUtils.httpResponse(contractManagerService.addNewContract(request));
    }

    @GetMapping("/get-contract/{groupId}")
    public ResponseEntity<Object> getContract(@RequestParam(name = "filter", required = false) String filter,
                                              @PathVariable Integer groupId){
        return ResponseUtils.httpResponse(contractManagerService.getAllContractWithFilter(filter, groupId));
    }
}
