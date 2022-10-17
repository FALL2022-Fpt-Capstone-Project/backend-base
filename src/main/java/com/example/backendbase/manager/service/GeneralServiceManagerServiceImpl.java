package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.GeneralService;
import com.example.backendbase.manager.repo.GeneralServiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralServiceManagerServiceImpl implements GeneralServiceManagerService{

    @Override
    public List<GeneralService> getAllGeneralServiceByContractId(Long id) {
        return null;
    }
}
