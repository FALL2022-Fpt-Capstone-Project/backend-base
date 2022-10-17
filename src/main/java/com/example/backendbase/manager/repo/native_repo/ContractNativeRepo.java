package com.example.backendbase.manager.repo.native_repo;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.manager.entity.*;
import com.example.backendbase.manager.entity.request.AddContractRequest;
import com.example.backendbase.manager.exception.ManagerException;
import com.example.backendbase.manager.repo.ContractRepo;
import com.example.backendbase.manager.repo.HandOverAssetsRepo;
import com.example.backendbase.manager.repo.HandOverGeneralServiceRepo;
import com.example.backendbase.manager.repo.RenterRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ContractNativeRepo {

    @PersistenceContext
    private EntityManager entityManager;

    private final RenterRepo renterRepo;

    private final HandOverAssetsRepo assetsRepo;
    private final ContractRepo contractRepo;

    private final HandOverGeneralServiceRepo generalServiceRepo;

    @Transactional
    public AddContractRequest addNewContract(AddContractRequest request) throws ManagerException {
        var contract = new Contracts();
        BeanUtils.copyProperties(request, contract);

        contract.setStartDate(TimeUtils.parseToTimestamp(request.getStartDate()));
        contract.setEndDate(TimeUtils.parseToTimestamp(request.getEndDate()));

        var listContractByGroupId = contractRepo.findAllByGroupId(request.getGroupId());
        var checkContractedRoom = listContractByGroupId.
                stream().
                filter(value -> Objects.equals(request.getRoomId(), value.getRoom())).
                findAny().orElse(null);
        if (!Objects.isNull(checkContractedRoom)) throw new ManagerException("Phòng đã đã có người thuê");

        if (request.getOldRenterId() != null) {
            contract.setRenters(request.getOldRenterId());
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
                    identity(Identity.builder().
                            identityFrontImg(request.getImage().get(0)).
                            identityBackImg(request.getImage().get(1)).build()).
                    address(Address.builder().build()).build()).getId();
            contract.setRenters(newRenterId);
            contract.setRoom(request.getRoomId());
            contract.setRenters(newRenterId);
            entityManager.persist(contract);
            entityManager.flush();
        }
        List<HandOverAssets> handOverAssets = new ArrayList<>();
        request.getBasicAssets().forEach(assets -> handOverAssets.add(
                HandOverAssets.builder()
                        .contractId(contract.getId())
                        .assetId(assets.getAssetsId())
                        .quantity(assets.getNumberOfAsset())
                        .dateOfDelivery(contract.getStartDate())
                        .build()));
        assetsRepo.saveAll(handOverAssets);

        List<HandOverGeneralService> handOverGeneralServices = new ArrayList<>();
        request.getHandOverGeneralServices().forEach(service ->
                    handOverGeneralServices.add(
                            HandOverGeneralService.builder()
                                    .handOverIndex(service.getHandOverIndex())
                                    .contractId(contract.getId())
                                    .generalServiceId(service.getGeneralServiceId())
                                    .dateOfDelivery(contract.getStartDate())
                                    .build()
                    ));
                generalServiceRepo.saveAll(handOverGeneralServices);
        return request;

    }
}
