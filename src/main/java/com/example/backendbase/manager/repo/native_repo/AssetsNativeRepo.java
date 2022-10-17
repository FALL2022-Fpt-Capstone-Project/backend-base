package com.example.backendbase.manager.repo.native_repo;

import com.example.backendbase.manager.entity.dto.HandOverAssetsDTO;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AssetsNativeRepo {

    @PersistenceContext
    private EntityManager entityManager;

    private final ModelMapper modelMapper;

    public List<HandOverAssetsDTO> findHandOverAssetsByContractId(Long id) {
        String statement = "SELECT basic_assets.asset_name, asset_type.asset_type_name, ho_assets.hand_over_asset_id, ho_assets.asset_id, ho_assets.contract_id, ho_assets.hand_over_asset_quantity, ho_assets.hand_over_asset_date_delivery, ho_assets.hand_over_asset_status, asset_type.asset_type_show_name\n" +
                "FROM manager_basic_assets basic_assets\n" +
                "INNER JOIN manager_hand_over_assets ho_assets\n" +
                "ON basic_assets.asset_id = ho_assets.asset_id\n" +
                "INNER JOIN manager_asset_types asset_type\n" +
                "ON CAST(basic_assets.asset_type_id AS INTEGER) = asset_type.asset_type_id \n" +
                "WHERE ho_assets.contract_id =?";
        Query query = entityManager.createNativeQuery(statement);
        query.setParameter(1, id);

        List<Object[]> resultList = query.getResultList();

        List<HandOverAssetsDTO> result = new ArrayList<>();
        resultList.forEach(record -> {
            HandOverAssetsDTO handOverAssetsDTO = new HandOverAssetsDTO();
            handOverAssetsDTO.setName((String) record[0]);
            handOverAssetsDTO.setTypeName((String) record[1]);
            handOverAssetsDTO.setId(((BigInteger) record[2]).longValue());
            handOverAssetsDTO.setAssetId(((BigInteger) record[3]).longValue());
            handOverAssetsDTO.setContractId(((BigInteger) record[4]).longValue());
            handOverAssetsDTO.setQuantity((Integer) record[5]);
            handOverAssetsDTO.setDateOfDelivery((Timestamp) record[6]);
            handOverAssetsDTO.setStatus((Boolean) record[7]);
            handOverAssetsDTO.setTypeShowName((String) record[8]);
            result.add(handOverAssetsDTO);
        });


        return result;
    }
}
