package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.AssetTypes;
import com.example.backendbase.manager.entity.BasicAssets;
import com.example.backendbase.manager.entity.dto.HandOverAssetsDTO;
import com.example.backendbase.manager.entity.request.AddBasicAssetRequest;
import com.example.backendbase.manager.exception.ManagerException;
import com.example.backendbase.manager.repo.AssetTypesRepo;
import com.example.backendbase.manager.repo.AssetsRepo;
import com.example.backendbase.manager.repo.HandOverAssetsRepo;
import com.example.backendbase.manager.repo.native_repo.AssetsNativeRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetMangerServiceImpl implements AssetMangerService{

    private final AssetsRepo assetsRepo;

    private final AssetTypesRepo assetTypesRepo;

    private final AssetsNativeRepo assetsNativeRepo;

    @Override
    public List<BasicAssets> getAllBasicAsset() {
        return assetsRepo.findAll();
    }

    @Override
    public BasicAssets updateBasicAsset(AddBasicAssetRequest request, Long id) {
        var assetsToUpdate = new BasicAssets();
        BeanUtils.copyProperties(request, assetsToUpdate);
        assetsToUpdate.setId(id);
        return assetsRepo.save(assetsToUpdate);
    }

    @Override
    public BasicAssets addBasicAsset(AddBasicAssetRequest request) {
        var basicAsset = new BasicAssets();
        BeanUtils.copyProperties(request, basicAsset);
        return assetsRepo.save(basicAsset);
    }

    @Override
    public void deleteAssetById(Long id) {
//        assetsRepo.delete(getHandOverAssetByContractId(id));
    }

    @Override
    @SneakyThrows
    public List<HandOverAssetsDTO> getHandOverAssetByContractId(Long id) {
        return assetsNativeRepo.findHandOverAssetsByContractId(id);
    }

    @Override
    public List<BasicAssets> filterBasicAssetByType(String condition) {
        // TODO
        return null;
    }

    @Override
    public List<AssetTypes> getAllAssetTypes() {
        return assetTypesRepo.findAll(Sort.by("id"));
    }

    @Override
    public List<HandOverAssetsDTO> updateHandOverAsset(Long contractId) {
        return null;
    }
}
