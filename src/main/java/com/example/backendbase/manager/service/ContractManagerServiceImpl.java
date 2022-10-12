package com.example.backendbase.manager.service;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.manager.ManagerConstant;
import com.example.backendbase.manager.entity.Contracts;
import com.example.backendbase.manager.entity.request.AddContractRequest;
import com.example.backendbase.manager.entity.response.ContractResponse;
import com.example.backendbase.manager.exception.ManagerException;
import com.example.backendbase.manager.repo.ContractRepo;
import com.example.backendbase.manager.repo.native_repo.ContractNativeRepo;
import com.example.backendbase.manager.repo.RenterRepo;
import com.twilio.rest.numbers.v2.regulatorycompliance.bundle.EvaluationReader;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContractManagerServiceImpl implements ContractManagerService {

    private final ContractNativeRepo contractNativeRepo;

    private final ContractRepo contractRepo;

    private final RenterRepo renterRepo;

    @Override
    public Object addNewContract(AddContractRequest request) throws ManagerException {
        return contractNativeRepo.addNewContract(request);
    }

    @Override
    public ContractResponse modifyContract(AddContractRequest addContractRequest) {
        return null;
    }

    @Override
    public String disableContract(Long id) {
        return null;
    }

    @Override
    public List<Contracts> getAllContractWithFilter(String condition, Integer groupId) {
        var listContract = contractRepo.findAllByGroupId(groupId.longValue());
        ArrayList<Contracts> contracts = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();


        if (Objects.isNull(condition)) {
            return contractRepo.findAllByGroupId(groupId.longValue());
        }
        if (condition.equals(ManagerConstant.EXPIRED_CONTRACT)) {
            listContract.forEach(value -> {
                if (value.getEndDate().compareTo(TimeUtils.getCurrentTime()) < 0) {
                    contracts.add(value);
                }
            });
            return contracts;
        }
        if (condition.equals(ManagerConstant.ALMOST_EXPIRED_CONTRACT)) {
            Timestamp conditionToCompare = TimeUtils.parseToTimestamp(now.plusMonths(3));
            return contractRepo.findAllByAlmostExpired(TimeUtils.parseToTimestamp(now), conditionToCompare);
        }
        if (condition.equals(ManagerConstant.LATEST_CONTRACT)) {
            Timestamp conditionToCompare = TimeUtils.parseToTimestamp(now.minusMonths(3));
            return contractRepo.findAllByLatest(conditionToCompare, TimeUtils.parseToTimestamp(now));
        }
        return null;
    }

    @Override
    public ContractResponse getContractById(Long id) {
        return null;
    }

    @Override
    public List<ContractResponse> searchFullTextContract(String condition) {
        return null;
    }
}
