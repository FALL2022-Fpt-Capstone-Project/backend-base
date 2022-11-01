package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;
import com.example.backendbase.manager.entity.request.AddBasicAssetRequest;
import com.example.backendbase.manager.service.AssetMangerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager/asset")
@RequiredArgsConstructor
public class AssetController {

    private final AssetMangerService assetMangerService;

    @GetMapping("/basic-asset")
    public ResponseEntity<Object> getAllBasicAsset() {
        return ResponseUtils.httpResponse(assetMangerService.getAllBasicAsset());
    }

    @GetMapping("/basic-asset/{assetId}")
    public ResponseEntity<Object> getBasicAssetById(@PathVariable Long assetId) {
        return ResponseUtils.httpResponse(assetMangerService.getBasicAsset(assetId));
    }

    @PostMapping("/add-basic-asset")
    public ResponseEntity<Object> addBasicAsset(@RequestBody AddBasicAssetRequest request) {
        return ResponseUtils.httpResponse(assetMangerService.addBasicAsset(request));
    }

    @PutMapping("/update-basic-asset/{basicAssetId}")
    public ResponseEntity<Object> updateBasicAsset(@RequestBody AddBasicAssetRequest request,
                                                   @PathVariable String basicAssetId) {
        return ResponseUtils.httpResponse(assetMangerService.updateBasicAsset(request, Long.parseLong(basicAssetId)));
    }

    @GetMapping("/hand-over/{contractId}")
    public ResponseEntity<Object> getAllHandOverAsset(@PathVariable Long contractId) {
        return ResponseUtils.httpResponse(assetMangerService.getHandOverAssetByContractId(contractId));
    }

    @PutMapping("/hand-over/update/{contractId}")
    public ResponseEntity<Object> updateHandOverAsset(@PathVariable Long contractId) {
        return ResponseUtils.httpResponse(assetMangerService.updateHandOverAsset(contractId));
    }

    @GetMapping("/type")
    public ResponseEntity<Object> getAllAssetTypes() {
        return ResponseUtils.httpResponse(assetMangerService.getAllAssetTypes());
    }

}
