package com.example.backendbase.manager.service;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.manager.entity.Address;
import com.example.backendbase.manager.entity.Buildings;
import com.example.backendbase.manager.entity.request.AddBuildingRequest;
import com.example.backendbase.manager.entity.request.UpdateBuildingRequest;
import com.example.backendbase.manager.entity.response.ListAllBuildingResponse;
import com.example.backendbase.manager.repo.BuildingRepo;
import com.example.backendbase.user.util.CurrentUserUtils;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingManagerServiceImpl implements IBuildingManager {

    private final BuildingRepo buildingRepo;

    @Override
    public Buildings addNewBuilding(AddBuildingRequest request) {
        return buildingRepo.save(Buildings.builder()
                .buildingName(request.getBuildingName())
                .totalRooms(request.getTotalRoom())
                .totalFloors(request.getTotalFloor())
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



    @Transactional
    public String updateBuilding(UpdateBuildingRequest request) {
        Buildings building = buildingRepo.findById(request.getBuildingId()).orElseThrow(() -> new RuntimeException("Error: Buildings is not found."));
        building.setBuildingName(request.getBuildingName());
        building.setTotalRooms(request.getTotalRoom());
        building.setTotalFloors(request.getTotalFloor());
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

    @Override
    public Buildings getBuilding(Long buildingId) {
        return buildingRepo.findById(buildingId).orElseThrow(() -> new RuntimeException("Error: Buildings is not found."));
    }

    @Override
    public List<ListAllBuildingResponse> getAllBuilding() {
        var listBuilding = buildingRepo.getAllBuilding();
        List<ListAllBuildingResponse> listAllBuildingResponses = new ArrayList<>();
        listBuilding.forEach(
                e -> {
                    listAllBuildingResponses.add(ListAllBuildingResponse.builder()
                            .id(e.getId())
                            .buildingName(e.getBuildingName())
                            .totalRooms(e.getTotalRooms())
                            .totalFloors(e.getTotalFloors())
                            .city(e.getAddress().getCity())
                            .wards(e.getAddress().getWards())
                            .district(e.getAddress().getDistrict())
                            .createdBy(e.getCreatedBy())
                            .updatedTime(e.getUpdatedTime())
                            .moreDetails(e.getAddress().getMoreDetails())
                            .build());
                }
        );
        return listAllBuildingResponses;
    }
}
