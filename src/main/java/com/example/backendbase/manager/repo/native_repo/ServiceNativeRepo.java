package com.example.backendbase.manager.repo.native_repo;

import com.example.backendbase.manager.entity.dto.GeneralServiceDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceNativeRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<GeneralServiceDTO> findAllGeneralServiceByContractId(Long id) {
        String statement = "SELECT basic_service.service_name, service_type.service_type_name, general_service.service_price, general_service.contract_id, basic_service.service_show_name\n" +
                "FROM manager_basic_service basic_service\n" +
                "INNER JOIN\n" +
                "manager_general_services general_service\n" +
                "ON\n" +
                "basic_service.service_id = general_service.service_id\n" +
                "INNER JOIN manager_service_type service_type\n" +
                "ON service_type.service_type_id = general_service.service_type_id\n" +
                "WHERE general_service.contract_id = ?";
        Query query = entityManager.createNativeQuery(statement);
        query.setParameter(1, id);

        List<Object[]> resultList = query.getResultList();

        List<GeneralServiceDTO> result = new ArrayList<>();
        resultList.forEach(record ->{
            GeneralServiceDTO handOverAssetsDTO = new GeneralServiceDTO();
            handOverAssetsDTO.setName((String) record[0]);
            handOverAssetsDTO.setServiceTypeName((String) record[1]);
            handOverAssetsDTO.setServicePrice((Double) record[2]);
            handOverAssetsDTO.setConntractId(((BigInteger)record[3]).longValue());
            handOverAssetsDTO.setServiceShowName((String) record[4]);
            result.add(handOverAssetsDTO);
        });


        return result;
    }
}
