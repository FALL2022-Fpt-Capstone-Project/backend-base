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

    @PutMapping("/update-contract/rooms/{contractId}")
    public ResponseEntity<Object> updateContract(@RequestBody AddContractRequest request,
                                                 @PathVariable Long contractId) throws ManagerException {
        return ResponseUtils.httpResponse(contractManagerService.updateContract(request, contractId));
    }


    @GetMapping("/get-contract/{id}")
    public ResponseEntity<Object> getContract(@RequestParam(name = "filter", required = false) String filter,
                                              @RequestParam(name = "duration", defaultValue = "1", required = false) String duration,
                                              @PathVariable Long id) {
        return ResponseUtils.httpResponse(contractManagerService.getAllContractWithFilter(filter, duration, id));
    }

    @GetMapping("/get-contract/rooms/{id}")
    public ResponseEntity<Object> getRoomContract(@PathVariable Long id) {
        return ResponseUtils.httpResponse(contractManagerService.getContractById(id));
    }

    @GetMapping("/statistical/get-contract/{id}")
    public ResponseEntity<Object> getNumberContractByDuration(@RequestParam(name = "duration", required = false, defaultValue = "1") String duration,
                                                              @PathVariable Long id) {
        return ResponseUtils.httpResponse(contractManagerService.getNumberOfContract(duration, id));
    }
}
