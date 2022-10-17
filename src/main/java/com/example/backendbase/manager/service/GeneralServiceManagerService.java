package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.GeneralService;

import java.util.List;

public interface GeneralServiceManagerService{
    List<GeneralService> getAllGeneralServiceByContractId(Long id);
}
