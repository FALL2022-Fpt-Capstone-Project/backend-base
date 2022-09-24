package com.example.backendbase.user.repo;

import com.example.backendbase.user.entity.UserInfor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserInformationRepo extends JpaRepository<UserInfor, Long> {
}
