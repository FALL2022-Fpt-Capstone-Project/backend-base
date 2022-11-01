package com.example.backendbase.manager.service;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.manager.entity.Address;
import com.example.backendbase.manager.entity.Buildings;
import com.example.backendbase.manager.entity.Contracts;
import com.example.backendbase.manager.entity.request.AddBuildingRequest;
import com.example.backendbase.manager.entity.request.UpdateBuildingRequest;
import com.example.backendbase.manager.entity.response.ListAllBuildingResponse;
import com.example.backendbase.manager.entity.response.RoomGroupResponse;
import com.example.backendbase.manager.exception.ManagerException;
import com.example.backendbase.manager.repo.*;
import com.example.backendbase.manager.repo.native_repo.AssetsNativeRepo;
import com.example.backendbase.manager.repo.native_repo.ServiceNativeRepo;
import com.example.backendbase.user.util.CurrentUserUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingManagerServiceImpl implements BuildingManagerService {

    private final BuildingRepo buildingRepo;

    private final RoomsRepo roomsRepo;

    private final GroupRepo groupRepo;

    private final AddressRepo addressRepo;

    private final ContractRepo contractRepo;

    private final AssetsNativeRepo assetsNativeRepo;

    private final ServiceNativeRepo serviceNativeRepo;

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
    @SneakyThrows
    public RoomGroupResponse getGroupById(Long groupId) {
        var roomGroup = groupRepo.findById(groupId).orElseThrow(() -> new ManagerException("Không tìm thấy group theo id"));
        var groupContract = contractRepo.findByGroupIdAndContractTerm(groupId, 0);
        var roomGroupAddress = addressRepo.findById(roomGroup.getAddress()).orElse(new Address());
        var roomListByGroup = roomsRepo.findAllByRoomGroups(groupId);

        return RoomGroupResponse.builder().
                id(roomGroup.getId()).
                name(roomGroup.getName()).
                address(roomGroupAddress).
                listRoom(roomListByGroup).
                listHandOverAssets(assetsNativeRepo.findHandOverAssetsByContractId(groupContract.getId())).
                listBasicServices(serviceNativeRepo.findAllGeneralServiceByContractId(groupContract.getId())).
                build();
    }

    @Override
    public Buildings getBuilding(Long buildingId) {
        return buildingRepo.findById(buildingId).orElseThrow(() -> new RuntimeException("Error: Buildings is not found."));
    }

    @Override
    public List<RoomGroupResponse> getAllBuilding() {
        var roomGroup = groupRepo.findAll();
        List<RoomGroupResponse> responses = new ArrayList<>();
        roomGroup.forEach(e -> {
            var contracts = contractRepo.findByGroupIdAndContractTerm(e.getId(), 0);
            var roomGroupAddress = addressRepo.findById(e.getAddress()).orElse(new Address());
            var roomListByGroup = roomsRepo.findAllByRoomGroups(e.getId());

            responses.add(RoomGroupResponse.builder().
                    id(e.getId()).
                    name(e.getName()).
                    address(roomGroupAddress).
                    listRoom(roomListByGroup).
                    listHandOverAssets(assetsNativeRepo.findHandOverAssetsByContractId(contracts.getId())).
                    listBasicServices(serviceNativeRepo.findAllGeneralServiceByContractId(contracts.getId())).
                    build()
            );
        });
        return responses;
    }
}
