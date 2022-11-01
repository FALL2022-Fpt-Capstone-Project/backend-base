package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.AssetTypes;
import com.example.backendbase.manager.entity.BasicAssets;
import com.example.backendbase.manager.entity.dto.HandOverAssetsDTO;
import com.example.backendbase.manager.entity.request.AddBasicAssetRequest;

import java.util.List;

public interface AssetMangerService {

    List<BasicAssets> getAllBasicAsset();

    BasicAssets updateBasicAsset(AddBasicAssetRequest request, Long id);

    BasicAssets addBasicAsset(AddBasicAssetRequest request);

    void deleteAssetById(Long id);

    List<HandOverAssetsDTO> getHandOverAssetByContractId(Long id);

    List<BasicAssets> filterBasicAssetByType(String condition);

    List<AssetTypes> getAllAssetTypes();

    List<HandOverAssetsDTO> updateHandOverAsset(Long contractId);

    Object getBasicAsset(Long assetId);
}
