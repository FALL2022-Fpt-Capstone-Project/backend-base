package com.example.backendbase.manager.service;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.manager.entity.Address;
import com.example.backendbase.manager.entity.Buildings;
import com.example.backendbase.manager.entity.request.AddBuildingRequest;
import com.example.backendbase.manager.entity.request.UpdateBuildingRequest;
import com.example.backendbase.manager.repo.AddressRepo;
import com.example.backendbase.manager.repo.BuildingRepo;
import com.example.backendbase.user.util.CurrentUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BuildingManagerService {

    private final BuildingRepo buildingRepo;

    private final AddressRepo addressRepo;

    public Buildings addNewBuilding(AddBuildingRequest request) {
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


    public String updateBuilding(UpdateBuildingRequest request) {
        Buildings building = buildingRepo.findById(request.getBuildingId()).orElseThrow(() -> new RuntimeException("Error: Buildings is not found."));
        building.setBuildingName(request.getBuildingName());
        building.setTotalRooms(request.getTotalRoom());

        //set address
        building.getAddress().setCity(request.getCity());
        building.getAddress().setDistrict(request.getDistrict());
        building.getAddress().setWards(request.getWards());
        building.getAddress().setMoreDetails(request.getMoreAddressDetail());
        building.getAddress().setCreatedBy(CurrentUserUtils.getCurrentUser());
        building.getAddress().setUpdatedTime(TimeUtils.getCurrentTime());


        building.setCreatedBy(CurrentUserUtils.getCurrentUser());
        building.setUpdatedTime(TimeUtils.getCurrentTime());

        //update
        buildingRepo.save(building);
        return "update successfully";
    }

    public Buildings getBuilding(Long buildingId) {
        return buildingRepo.findById(buildingId).orElseThrow(() -> new RuntimeException("Error: Buildings is not found."));
    }
}
