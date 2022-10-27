package com.example.backendbase.manager.repo.native_repo;

import com.example.backendbase.manager.entity.dto.GeneralServiceDTO;
import com.example.backendbase.manager.entity.dto.HandOverGeneralServiceDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceNativeRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<GeneralServiceDTO> findAllGeneralServiceByContractId(Long id) {
        String statement = "SELECT basic_service.service_name,\n" +
                "       service_type.service_type_name,\n" +
                "       general_service.service_price,\n" +
                "       general_service.contract_id,\n" +
                "       basic_service.service_show_name,\n" +
                "       general_service.service_type_id,\n" +
                "       general_service.general_service_id, \n" +
                "       general_service.note,\n" +
                "       basic_service.service_id\n" +
                "FROM manager_basic_service basic_service\n" +
                "INNER JOIN\n" +
                "manager_general_services general_service\n" +
                "ON basic_service.service_id = general_service.service_id\n" +
                "INNER JOIN manager_service_type service_type\n" +
                "ON service_type.service_type_id = general_service.service_type_id\n" +
                "WHERE general_service.contract_id = ?;";
        Query query = entityManager.createNativeQuery(statement);
        query.setParameter(1, id);

        List<Object[]> resultList = query.getResultList();

        List<GeneralServiceDTO> result = new ArrayList<>();
        resultList.forEach(record -> {
            GeneralServiceDTO handOverServiceDTO = new GeneralServiceDTO();
            handOverServiceDTO.setName((String) record[0]);
            handOverServiceDTO.setServiceTypeName((String) record[1]);
            handOverServiceDTO.setServicePrice((Double) record[2]);
            handOverServiceDTO.setConntractId(((BigInteger) record[3]).longValue());
            handOverServiceDTO.setServiceShowName((String) record[4]);
            handOverServiceDTO.setServiceType(((BigInteger) record[5]).longValue());
            handOverServiceDTO.setId(((BigInteger) record[6]).longValue());
            handOverServiceDTO.setNote((String) record[7]);
            handOverServiceDTO.setServiceId(((Integer) record[8]).longValue());
            result.add(handOverServiceDTO);
        });
        return result;
    }

    public List<HandOverGeneralServiceDTO> findAllHandOverServiceByContractId(Long id) {
        String statement = "SELECT basic_service.service_name,\n" +
                "       service_type.service_type_name,\n" +
                "       general_service.service_price,\n" +
                "       general_service.contract_id,\n" +
                "       basic_service.service_show_name,\n" +
                "       general_service.service_type_id,\n" +
                "       general_service.general_service_id,\n" +
                "       general_service.note,\n" +
                "       basic_service.service_id,\n" +
                "       hand_over_services.hand_over_general_service_index,\n" +
                "       hand_over_services.hand_over_general_service_date_delivery\n" +
                "FROM manager_basic_service basic_service\n" +
                "         INNER JOIN manager_general_services general_service\n" +
                "                    ON basic_service.service_id = general_service.service_id\n" +
                "         INNER JOIN manager_service_type service_type\n" +
                "                    ON service_type.service_type_id = general_service.service_type_id\n" +
                "         INNER JOIN manager_hand_over_general_services hand_over_services\n" +
                "                    on general_service.general_service_id = hand_over_services.general_service_id\n" +
                "WHERE hand_over_services.contract_id = ?;";
        Query query = entityManager.createNativeQuery(statement);
        query.setParameter(1, id);

        List<Object[]> resultList = query.getResultList();

        List<HandOverGeneralServiceDTO> result = new ArrayList<>();
        resultList.forEach(record -> {
            HandOverGeneralServiceDTO handOverServiceDTO = new HandOverGeneralServiceDTO();
            handOverServiceDTO.setName((String) record[0]);
            handOverServiceDTO.setServiceTypeName((String) record[1]);
            handOverServiceDTO.setServicePrice((Double) record[2]);
            handOverServiceDTO.setConntractId(((BigInteger) record[3]).longValue());
            handOverServiceDTO.setServiceShowName((String) record[4]);
            handOverServiceDTO.setServiceType(((BigInteger) record[5]).longValue());
            handOverServiceDTO.setId(((BigInteger) record[6]).longValue());
            handOverServiceDTO.setNote((String) record[7]);
            handOverServiceDTO.setServiceId(((Integer) record[8]).longValue());
            handOverServiceDTO.setIndex((Integer) record[9]);
            handOverServiceDTO.setDateDelivery((Timestamp) record[10]);
            result.add(handOverServiceDTO);
        });
        return result;
    }
}
