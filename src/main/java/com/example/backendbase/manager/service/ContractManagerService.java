package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.Contracts;
import com.example.backendbase.manager.entity.request.AddContractRequest;
import com.example.backendbase.manager.entity.response.ContractResponse;
import com.example.backendbase.manager.exception.ManagerException;

import java.util.List;

public interface ContractManagerService {
    Object addNewContract(AddContractRequest addContractRequest) throws ManagerException;

    ContractResponse modifyContract(AddContractRequest addContractRequest);

    String disableContract(Long id);

    List<Contracts> getAllContractWithFilter(String condition, Integer buldingId);

    ContractResponse getContractById(Long id);

    List<ContractResponse> searchFullTextContract(String condition);
}
