package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.BasicService;
import com.example.backendbase.manager.entity.GeneralService;
import com.example.backendbase.manager.entity.ServiceType;
import com.example.backendbase.manager.entity.dto.GeneralServiceDTO;
import com.example.backendbase.manager.entity.request.AddNewGeneralServiceRequest;
import com.example.backendbase.manager.repo.BasicServiceRepo;
import com.example.backendbase.manager.repo.GeneralServiceRepo;
import com.example.backendbase.manager.repo.ServiceTypeRepo;
import com.example.backendbase.manager.repo.native_repo.ServiceNativeRepo;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralServiceManagerServiceImpl implements GeneralServiceManagerService {

    private final BasicServiceRepo basicServiceRepo;

    private final ServiceNativeRepo serviceNativeRepo;

    private final GeneralServiceRepo generalServiceRepo;

    private final ServiceTypeRepo serviceTypeRepo;


    @Override
    public List<GeneralServiceDTO> getAllGeneralServiceByContractId(Long id) {
        return serviceNativeRepo.findAllGeneralServiceByContractId(id);
    }

    @Override
    public List<BasicService> getBasicService() {
        return basicServiceRepo.findAll();
    }

    @Override
    public void deleteGeneralService(Long generalServiceId) {
        generalServiceRepo.delete(generalServiceRepo.findById(generalServiceId).get());
    }

    @Override
    public GeneralService addNewGeneralService(AddNewGeneralServiceRequest request) {
        return generalServiceRepo.save(GeneralService.builder()
                .serviceType(request.getGeneralServiceType())
                .servicePrice(request.getGeneralServicePrice())
                .serviceId(request.getServiceId())
                .conntractId(request.getContractId()).build());
    }

    @Override
    public GeneralService updateGeneralService(Long generalServiceId, AddNewGeneralServiceRequest addNewGeneralServiceRequest) {
        var generalService = generalServiceRepo.findById(generalServiceId).get();
        generalService.setServiceType(addNewGeneralServiceRequest.getGeneralServiceType());
        generalService.setServicePrice(addNewGeneralServiceRequest.getGeneralServicePrice());
        generalService.setServiceId(addNewGeneralServiceRequest.getServiceId());
        return generalServiceRepo.save(generalService);
    }

    @Override
    public List<ServiceType> getServiceType() {
        return serviceTypeRepo.findAll();
    }

    @Override
    public GeneralService getGeneralServiceById(Long generalServiceId) {
        return generalServiceRepo.findById(generalServiceId).get();
    }
}
