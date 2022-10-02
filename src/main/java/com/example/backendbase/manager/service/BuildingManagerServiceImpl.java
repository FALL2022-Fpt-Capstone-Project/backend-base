package com.example.backendbase.manager.service;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.manager.entity.Address;
import com.example.backendbase.manager.entity.Buildings;
import com.example.backendbase.manager.entity.request.AddBuildingRequest;
import com.example.backendbase.manager.repo.BuildingRepo;
import com.example.backendbase.user.util.CurrentUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingManagerServiceImpl implements IBuildingManager{

    private final BuildingRepo buildingRepo;

    @Override
    public Buildings addNewBuilding(AddBuildingRequest request){
        return buildingRepo.save(Buildings.builder()
                .buildingName(request.getBuildingName())
                .totalRooms(request.getTotalRoom())
                .address(Address.builder().city(request.getCity())
                                .district(request.getDistrict())
                                .wards(request.getWards())
                                .moreDetails(request.getMoreAddressDetail())
                                .createdBy(CurrentUserUtils.getCurrentUser())
                                .updatedTime(TimeUtils.getCurrentTime())
                                .build())
                .createdBy(CurrentUserUtils.getCurrentUser())
                .updatedTime(TimeUtils.getCurrentTime())
                .build());
    }

    @Override
    public List<Buildings> getAllBuilding() {
        return buildingRepo.getAllBuilding();
    }
}
