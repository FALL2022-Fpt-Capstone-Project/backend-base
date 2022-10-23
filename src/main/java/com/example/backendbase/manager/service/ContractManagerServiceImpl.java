package com.example.backendbase.manager.service;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.manager.constant.ManagerConstant;
import com.example.backendbase.manager.entity.Contracts;
import com.example.backendbase.manager.entity.request.AddContractRequest;
import com.example.backendbase.manager.entity.response.ContractResponse;
import com.example.backendbase.manager.entity.response.NumberOfContractResponse;
import com.example.backendbase.manager.exception.ManagerException;
import com.example.backendbase.manager.repo.ContractRepo;
import com.example.backendbase.manager.repo.native_repo.ContractNativeRepo;
import com.example.backendbase.manager.repo.RenterRepo;
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
    public AddContractRequest addNewContract(AddContractRequest request) throws ManagerException {
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
    public List<ContractResponse> getAllContractWithFilter(String condition, String duration, Long groupId) {
        int durationTime = Integer.parseInt(duration);
        var listContract = contractRepo.findAllByGroupIdAndContractTerm(groupId, 1);
        List<ContractResponse> contractResponses = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();

        if (Objects.isNull(condition)) {
            listContract.forEach(value -> contractResponses.add(buildContractResponse(value)));
            return contractResponses;
        }

        if (condition.equals(ManagerConstant.EXPIRED_CONTRACT)) {
            listContract.forEach(value -> {
                if (value.getEndDate().compareTo(TimeUtils.getCurrentTime()) < 0) {
                    contractResponses.add(buildContractResponse(value));
                }
            });
            return contractResponses;
        }

        if (condition.equals(ManagerConstant.ALMOST_EXPIRED_CONTRACT)) {
            Timestamp conditionToCompare = TimeUtils.parseToTimestamp(now.plusMonths(durationTime));
            contractRepo
                    .findAllByAlmostExpired(TimeUtils.parseToTimestamp(now), conditionToCompare, groupId)
                    .forEach(contract1 -> contractResponses.add(buildContractResponse(contract1)));
            return contractResponses;
        }

        if (condition.equals(ManagerConstant.LATEST_CONTRACT)) {
            Timestamp conditionToCompare = TimeUtils.parseToTimestamp(now.minusMonths(durationTime));
            contractRepo
                    .findAllByLatest(conditionToCompare, TimeUtils.parseToTimestamp(now), groupId)
                    .forEach(contract2 -> contractResponses.add(buildContractResponse(contract2)));
            return contractResponses;
        }

        return contractResponses;
    }

    public ContractResponse buildContractResponse(Contracts contracts) {
        ContractResponse contractResponse = new ContractResponse();
        var renters = renterRepo.findById(contracts.getRenters()).get();
        contractResponse.setRepresentRenterName(renters.getRenterFullName());
        contractResponse.setContractName(contracts.getContractName());
        contractResponse.setDeposit(contracts.getDeposit());
        contractResponse.setPrice(contracts.getPrice());
        contractResponse.setStartDate(contracts.getStartDate());
        contractResponse.setEndDate(contracts.getEndDate());
        contractResponse.setIsDisable(contracts.getIsDisable());
        return contractResponse;
    }


    @Override
    public ContractResponse getContractById(Long id) {
        return null;
    }

    @Override
    public List<ContractResponse> searchFullTextContract(String condition) {
        return null;
    }

    @Override

    public NumberOfContractResponse getNumberOfContract(String filter, Long id) {
        int duration = Integer.parseInt(filter);
        LocalDateTime now = LocalDateTime.now();

        Timestamp almostExpiredTime = TimeUtils.parseToTimestamp(LocalDateTime.now().plusMonths(duration));
        Integer totalAlmostExpired =
                contractRepo.findAllByAlmostExpired(TimeUtils.parseToTimestamp(now), almostExpiredTime, id).size();


        Timestamp latestTime = TimeUtils.parseToTimestamp(now.minusMonths(3));
        Integer totalLatest = contractRepo.findAllByLatest(latestTime, TimeUtils.parseToTimestamp(now), id).size();

        Integer totalExpired =
                contractRepo.findAllExpriedContract(TimeUtils.parseToTimestamp(now), id).size();

        return NumberOfContractResponse.builder()
                .duration(duration)
                .expiredContract(totalExpired)
                .almostExpiredContract(totalAlmostExpired)
                .latestContract(totalLatest).build();
    }
}
