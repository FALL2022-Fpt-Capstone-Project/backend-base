package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.Address;
import com.example.backendbase.manager.entity.Buildings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

}
