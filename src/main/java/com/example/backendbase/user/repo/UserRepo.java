package com.example.backendbase.user.repo;

import com.example.backendbase.security.enums.ERole;
import com.example.backendbase.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

//    List<User> findAllByRoles_NameAAndIsOwner(ERole role, boolean isOwner);
//
//    List<User> findAllByRoles_NameAAndIsDeactive(ERole role, boolean isDeactive);

    List<User> findAllByIsOwner(boolean isOwner);

    User deleteUserByUsername(String username);


}
