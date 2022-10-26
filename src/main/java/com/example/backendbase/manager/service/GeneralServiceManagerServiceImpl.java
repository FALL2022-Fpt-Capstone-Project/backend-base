package com.example.backendbase.manager.service;

import com.example.backendbase.manager.constant.ManagerConstant;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .note(request.getNote())
                .conntractId(request.getContractId()).build());
    }

    @Override
    public GeneralService updateGeneralService(Long generalServiceId, AddNewGeneralServiceRequest addNewGeneralServiceRequest) {
        var generalService = generalServiceRepo.findById(generalServiceId).get();
        generalService.setServiceType(addNewGeneralServiceRequest.getGeneralServiceType());
        generalService.setServicePrice(addNewGeneralServiceRequest.getGeneralServicePrice());
        generalService.setServiceId(addNewGeneralServiceRequest.getServiceId());
        generalService.setNote(addNewGeneralServiceRequest.getNote());
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

    @Override
    public List<GeneralService> quickAddGeneralService(Long contractId) {

        GeneralService defaultElectricService =
                new GeneralService(ManagerConstant.SERVICE_ELECTRIC, contractId, ManagerConstant.SERVICE_TYPE_METER, ManagerConstant.ELECTRIC_DEFAULT_PRICE);
        GeneralService defaultWaterService =
                new GeneralService(ManagerConstant.SERVICE_WATER, contractId, ManagerConstant.SERVICE_TYPE_METER, ManagerConstant.WATER_DEFAULT_PRICE);
        GeneralService defaultInternetService =
                new GeneralService(ManagerConstant.SERVICE_INTERNET, contractId, ManagerConstant.SERVICE_TYPE_MONTH, ManagerConstant.INTERNET_DEFAULT_PRICE);
        GeneralService defaultVehiclesService =
                new GeneralService(ManagerConstant.SERVICE_VEHICLES, contractId, ManagerConstant.SERVICE_TYPE_PERSON, ManagerConstant.VEHICLES_DEFAULT_PRICE);

        List<GeneralService> defaultGeneralServiceList = new ArrayList<>();
        defaultGeneralServiceList.add(defaultElectricService);
        defaultGeneralServiceList.add(defaultWaterService);
        defaultGeneralServiceList.add(defaultInternetService);
        defaultGeneralServiceList.add(defaultVehiclesService);

        var generalService = generalServiceRepo.findAllByConntractId(contractId);
        Map<Long, GeneralService> map = new HashMap<>();
        generalService.forEach(e->map.put(e.getServiceId(), e));

        defaultGeneralServiceList.forEach(e1->{
           if(map.get(e1.getServiceId()) == null){
               generalService.add(e1);
           }
        });
        return generalServiceRepo.saveAll(generalService);
    }
}
