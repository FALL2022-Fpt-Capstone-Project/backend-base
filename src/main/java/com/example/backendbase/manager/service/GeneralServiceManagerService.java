package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.BasicService;
import com.example.backendbase.manager.entity.GeneralService;
import com.example.backendbase.manager.entity.ServiceType;
import com.example.backendbase.manager.entity.dto.GeneralServiceDTO;
import com.example.backendbase.manager.entity.request.AddNewGeneralServiceRequest;

import java.util.List;

public interface GeneralServiceManagerService{
    List<GeneralServiceDTO> getAllGeneralServiceByContractId(Long id);

    List<BasicService> getBasicService();

    void deleteGeneralService(Long generalServiceId);

    GeneralService addNewGeneralService(AddNewGeneralServiceRequest generalService);

    GeneralService updateGeneralService(Long generalServiceId, AddNewGeneralServiceRequest addNewGeneralServiceRequest);

    List<ServiceType> getServiceType();

    GeneralService getGeneralServiceById(Long generalServiceId);

    List<GeneralService> quickAddGeneralService(Long contractId);
}
