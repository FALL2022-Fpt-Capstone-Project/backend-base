package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.Contracts;
import com.example.backendbase.manager.entity.dto.RoomContractDTO;
import com.example.backendbase.manager.entity.request.AddContractRequest;
import com.example.backendbase.manager.entity.response.ContractResponse;
import com.example.backendbase.manager.entity.response.NumberOfContractResponse;
import com.example.backendbase.manager.exception.ManagerException;

import java.util.List;

public interface ContractManagerService {
    AddContractRequest addNewContract(AddContractRequest addContractRequest) throws ManagerException;

    ContractResponse modifyContract(AddContractRequest addContractRequest);

    String disableContract(Long id);

    List<ContractResponse> getAllContractWithFilter(String condition, String duration, Long id);

    ContractResponse getContractById(Long id);

    List<ContractResponse> searchFullTextContract(String condition);

    NumberOfContractResponse getNumberOfContract(String filter, Long id);

    RoomContractDTO getRoomContract(Long id);
}
