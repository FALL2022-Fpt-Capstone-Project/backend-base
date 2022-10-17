package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.Renters;
import com.example.backendbase.manager.repo.RenterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RenterMangerServiceImpl implements RenterManagerService {
    private final RenterRepo renterRepo;
    @Override
    public List<Renters> getOldRenter() {
        return renterRepo.findAll();
    }
}
