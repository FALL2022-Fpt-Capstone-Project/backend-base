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
    List<User> findAllByIsDeactiveAndRoles_NameIn(boolean isDeactive, List<ERole> roles);

    List<User> findAllByIsOwnerAndRoles_NameInAndIsDeactive(boolean isOwner, List<ERole> roles, boolean isActivate);

    List<User> findAllByIsOwnerAndRoles_NameInAndIsDeactiveOrderByCreatedDateDesc(boolean isOwner, List<ERole> roles, boolean isActivate);

    List<User> findAllByIsOwnerAndRoles_NameInAndIsDeactiveOrderByCreatedDateAsc(boolean isOwner, List<ERole> roles, boolean isActivate);

    List<User> findAllByIsOwnerAndFullNameContaining(boolean isOwner, String nameToSearch);

    List<User> findAllByIsOwnerAndGenderIs(boolean isOwner, String genderToSearch);
    List<User> findAllByIsOwner(boolean isOwner);

    User deleteUserByUsername(String username);



}
