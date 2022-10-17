package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;
import com.example.backendbase.manager.repo.AssetsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager/asset")
@RequiredArgsConstructor
public class AssetController {

    private final AssetsRepo assetsRepo;

    @GetMapping("/basic-asset")
    public ResponseEntity<Object> getAllBasicAsset() {
        return ResponseUtils.httpResponse(assetsRepo.findAll());
    }

}
