package com.example.backendbase.manager.repo.native_repo;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.manager.entity.Address;
import com.example.backendbase.manager.entity.Contracts;
import com.example.backendbase.manager.entity.Identity;
import com.example.backendbase.manager.entity.Renters;
import com.example.backendbase.manager.entity.request.AddContractRequest;
import com.example.backendbase.manager.exception.ManagerException;
import com.example.backendbase.manager.repo.ContractRepo;
import com.example.backendbase.manager.repo.RenterRepo;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Objects;

@Repository
public class ContractNativeRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RenterRepo renterRepo;

    @Autowired
    private ContractRepo contractRepo;

    @Transactional
    public Contracts addNewContract(AddContractRequest request) throws ManagerException {
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
                    fullName(request.getRenterName()).
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
        }
        return contract;

    }
}
