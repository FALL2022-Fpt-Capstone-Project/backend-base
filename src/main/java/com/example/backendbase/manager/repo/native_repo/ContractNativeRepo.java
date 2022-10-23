package com.example.backendbase.manager.repo.native_repo;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.manager.entity.*;
import com.example.backendbase.manager.entity.request.AddContractRequest;
import com.example.backendbase.manager.exception.ManagerException;
import com.example.backendbase.manager.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ContractNativeRepo {

    @PersistenceContext
    private EntityManager entityManager;
    private final RenterRepo renterRepo;
    private final HandOverAssetsRepo assetsRepo;
    private final ContractRepo contractRepo;
    private final HandOverGeneralServiceRepo generalServiceRepo;
    private final BasicAssetsRepo basicAssetsRepo;

    @Transactional
    public AddContractRequest addNewContract(AddContractRequest request) throws ManagerException {
        var contract = new Contracts();
        BeanUtils.copyProperties(request, contract);

        contract.setStartDate(TimeUtils.parseToTimestamp(request.getStartDate()));
        contract.setEndDate(TimeUtils.parseToTimestamp(request.getEndDate()));

        //thông tin phòng
        var listRoomContract = contractRepo.findAllByGroupId(request.getGroupId());

        //lấy thông tin hợp đồng của tòa
        var groupContract = contractRepo.findByGroupIdAndContractTerm(request.getGroupId(), request.getContractTerm());
        var checkContractedRoom = listRoomContract.
                stream().
                filter(value -> Objects.equals(request.getRoomId(), value.getRoom())).
                findAny().orElse(null);
        //check xem phòng có hợp đông chưa
        if (!Objects.isNull(checkContractedRoom)) throw new ManagerException("Phòng đã đã có người thuê");

        if (request.getOldRenterId() != null) {
            contract.setRenters(request.getOldRenterId());
            var updateOldRenter = renterRepo.findById(request.getOldRenterId());
            updateOldRenter.get().setRepresent(true);
            renterRepo.save(updateOldRenter.get());
            contract.setRoom(request.getRoomId());
            entityManager.persist(contract);
        } else {
            String gender = "Nam";
            if (Boolean.FALSE.equals(request.getGender())) gender = "Nữ";
            Long newRenterId = renterRepo.save(Renters.builder().
                    renterFullName(request.getRenterName()).
                    gender(gender).
                    phoneNumber(request.getPhoneNumber()).
                    email(request.getEmail()).
                    identityNumber(request.getIdentityCard()).
                    identity(Identity.builder().build()).
                    represent(true).
                    address(Address.builder().build()).build()).getId();
            contract.setRenters(newRenterId);
            contract.setRoom(request.getRoomId());
            contract.setRenters(newRenterId);
            entityManager.persist(contract);
            entityManager.flush();
        }
        if (!request.getHandOverGeneralServices().isEmpty()) {
            List<HandOverAssets> handOverAssets = new ArrayList<>();

            List<HandOverAssets> additionalAssets = new ArrayList<>();
            request.getBasicAssets().forEach(assets -> {
                //Add thêm tài sản không thuộc tòa
                if (!Objects.isNull(assets.getAssetsAdditionalName())) {
                    //tài sản cơ bản
                    Long basicAssetsId = basicAssetsRepo.save(BasicAssets.builder()
                            .name(assets.getAssetsAdditionalName())
                            .build()).getId();

                    //tài sản chung cho tòa để quản lý
                    additionalAssets.add(HandOverAssets.builder()
                            .contractId(groupContract.getId())
                            .quantity(assets.getNumberOfAsset())
                            .status(assets.getHandOverAssetStatus())
                            .assetId(basicAssetsId)
                            .build());

                    //tài sản bàn giao cho phòng
                    handOverAssets.add(HandOverAssets.builder()
                            .contractId(contract.getId())
                            .quantity(assets.getNumberOfAsset())
                            .status(assets.getHandOverAssetStatus())
                            .assetId(basicAssetsId)
                            .build());

                    handOverAssets.addAll(additionalAssets);
                }

                handOverAssets.add(
                        HandOverAssets.builder()
                                .contractId(contract.getId())
                                .assetId(assets.getAssetsId())
                                .quantity(assets.getNumberOfAsset())
                                .status(assets.getHandOverAssetStatus())
                                .dateOfDelivery(contract.getStartDate())
                                .build());
            });
            assetsRepo.saveAll(handOverAssets);
            assetsRepo.saveAll(assetUpdateQuantity(request, contract.getId()));
        }

        if (!request.getHandOverGeneralServices().isEmpty()) {
            List<HandOverGeneralService> handOverGeneralServices = new ArrayList<>();
            request.getHandOverGeneralServices().forEach(service ->
                    handOverGeneralServices.add(
                            HandOverGeneralService.builder()
                                    .handOverIndex(service.getHandOverServiceIndex())
                                    .contractId(contract.getId())
                                    .generalServiceId(service.getGeneralServiceId())
                                    .dateOfDelivery(contract.getStartDate())
                                    .build()
                    ));
            generalServiceRepo.saveAll(handOverGeneralServices);
        }

        if (!request.getMember().isEmpty()) {
            List<Renters> members = new ArrayList<>();
            request.getMember().forEach(member -> {
                String gender = "Nam";
                if (Boolean.FALSE.equals(request.getGender())) gender = "Nữ";
                members.add(Renters.builder().
                        renterFullName(request.getRenterName()).
                        gender(gender).
                        phoneNumber(request.getPhoneNumber()).
                        email(request.getEmail()).
                        identityNumber(request.getIdentityCard()).
                        identity(Identity.builder().build()).
                        represent(true).
                        address(Address.builder().build()).build());
            });
            renterRepo.saveAll(members);
        }
        return request;
    }

    public List<HandOverAssets> assetUpdateQuantity(AddContractRequest addContractRequest, Long groupContractId) {
        Map<Long, HandOverAssets> assetMap = new HashMap<>();
        var groupGeneralAssets = assetsRepo.findAllByContractId(groupContractId);
        groupGeneralAssets.forEach(groupAsset -> assetMap.put(groupAsset.getId(), groupAsset));
        addContractRequest.getBasicAssets().forEach(roomAsset -> {
            var assetToUpdateQuantity = assetMap.get(roomAsset.getAssetsId());
            assetToUpdateQuantity.setQuantity(assetToUpdateQuantity.getQuantity() - roomAsset.getNumberOfAsset());
        });
        return new ArrayList<>(assetMap.values());
    }
}
