package com.example.backendbase.manager.service;

import com.example.backendbase.common.utils.ParseUtils;
import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.manager.constant.ManagerConstant;
import com.example.backendbase.manager.exception.ManagerException;
import com.example.backendbase.security.enums.ERole;
import com.example.backendbase.user.entity.Role;
import com.example.backendbase.user.entity.User;
import com.example.backendbase.manager.entity.request.ChangePassRequest;
import com.example.backendbase.manager.entity.request.ModifyAccountRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;
import com.example.backendbase.manager.entity.response.AddAssistantAccountResponse;
import com.example.backendbase.manager.entity.response.ChangeAssistantPassResponse;
import com.example.backendbase.manager.entity.response.StaffAccountResponse;
import com.example.backendbase.user.repo.RoleRepo;
import com.example.backendbase.user.repo.UserRepo;
import com.example.backendbase.user.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffManagerServiceImpl implements StaffManagerService {

    private final UserRepo userRepository;

    private final RoleRepo roleRepository;

    private final PasswordEncoder encoder;

    private final IUserService userService;

    @Override
    @SneakyThrows
    public StaffAccountResponse updateAccount(ModifyAccountRequest changeRequest, Long staffId) {
        Optional<User> userToUpdateRole;

        userToUpdateRole = userRepository.findById(staffId);
        if (!userToUpdateRole.isPresent()) throw new UsernameNotFoundException("AccountId Not Found!!");
        if(Objects.isNull(changeRequest.getDeactivate())) changeRequest.setDeactivate(false);
        userToUpdateRole.get().setUsername(changeRequest.getUserName());
        userToUpdateRole.get().setGender(changeRequest.getGender());
        userToUpdateRole.get().setFullName(changeRequest.getFullName());
        userToUpdateRole.get().setPhoneNumber(changeRequest.getPhoneNumber());
        userToUpdateRole.get().setEPassword(userToUpdateRole.get().getEPassword());
        userToUpdateRole.get().setCreatedDate(userToUpdateRole.get().getCreatedDate());
        userToUpdateRole.get().setIsDeactive(changeRequest.getDeactivate());
        userToUpdateRole.get().getAddress().setMoreDetails(changeRequest.getMoreDetails());
        userToUpdateRole.get().setPermission(ParseUtils.parseIntArrayToString(changeRequest.getPermission()));
        if (!Objects.isNull(changeRequest.getRole())) {
            userToUpdateRole.get().setRoles(roleChecker(changeRequest.getRole()));
        }
        var afterUpdate = userRepository.save(userToUpdateRole.get());
        List<User> users = new ArrayList<>();
        users.add(afterUpdate);

        return buildAssistantAccountByFilter(users).get(0);

    }

    @Override
    public List<StaffAccountResponse> getListAssistantAccount(String condition, String roles, int deactivate, String permission, String startDate, String endDate) {
        List<ERole> listRole = new ArrayList<>();
        List<String> listStringRole = new ArrayList<>();

        if (roles.equals("all")) {
            listRole = (List<ERole>) ParseUtils.getAllEnum(ERole.class);
            listStringRole = ParseUtils.getAllNameEnumClass(ERole.class);
        } else {
            if (roles.equals("admin")) {
                listRole.add(ERole.ROLE_ADMIN);
                listStringRole.add(ERole.ROLE_ADMIN.name());
            }
            if (roles.equals("staff")) {
                listRole.add(ERole.ROLE_STAFF);
                listStringRole.add(ERole.ROLE_STAFF.name());
            }
        }

        if (deactivate == -1) {
            if (!Objects.isNull(startDate) && !Objects.isNull(endDate)) {
                switch (condition) {
                    case "latest":
                        return buildAssistantAccountByFilter(
                                userRepository.findAllStaffWithFilterOrderAsc(listStringRole, TimeUtils.parseToTimestamp(startDate), TimeUtils.parseToTimestamp(endDate)));
                    case "oldest":
                        return buildAssistantAccountByFilter(
                                userRepository.findAllStaffWithFilterOrderDsc(listStringRole, TimeUtils.parseToTimestamp(startDate), TimeUtils.parseToTimestamp(endDate)));
                }
            }

            switch (condition) {
                case "latest":
                    return buildAssistantAccountByFilter(
                            userRepository.findAllByIsOwnerAndRoles_NameInOrderByCreatedDateDesc(false, listRole));
                case "oldest":
                    return buildAssistantAccountByFilter(
                            userRepository.findAllByIsOwnerAndRoles_NameInOrderByCreatedDateAsc(false, listRole));
            }
            return buildAssistantAccountByFilter(userRepository.findAllByIsOwnerAndRoles_NameInAndIsDeactiveOrderByCreatedDateDesc(false, listRole, false));
        }
        //activate == 1 => true
        boolean isDeactivate = true;
        if (deactivate == ManagerConstant.DEACTIVATE_ACCOUNT)
            isDeactivate = false;

        if (!Objects.isNull(startDate) && !Objects.isNull(endDate)) {
            switch (condition) {
                case "latest":
                    return buildAssistantAccountByFilter(
                            userRepository.findAllStaffWithFilterStatusOrderAsc(listStringRole, isDeactivate, TimeUtils.parseToTimestamp(startDate), TimeUtils.parseToTimestamp(endDate)));
                case "oldest":
                    return buildAssistantAccountByFilter(
                            userRepository.findAllStaffWithFilterStatusOrderDsc(listStringRole, isDeactivate, TimeUtils.parseToTimestamp(startDate), TimeUtils.parseToTimestamp(endDate)));
            }
        }

        switch (condition) {
            case "latest":
                return buildAssistantAccountByFilter(
                        userRepository.findAllByIsOwnerAndRoles_NameInAndIsDeactiveOrderByCreatedDateDesc(false, listRole, isDeactivate));
            case "oldest":
                return buildAssistantAccountByFilter(
                        userRepository.findAllByIsOwnerAndRoles_NameInAndIsDeactiveOrderByCreatedDateAsc(false, listRole, isDeactivate));
        }

        return buildAssistantAccountByFilter(userRepository.findAllByIsOwnerAndRoles_NameInAndIsDeactiveOrderByCreatedDateDesc(false, listRole, isDeactivate));
    }

    public List<StaffAccountResponse> buildAssistantAccountByFilter(List<User> listAssistantAccount) {
        List<StaffAccountResponse> response = new ArrayList<>();
        listAssistantAccount.forEach(user -> response.add(StaffAccountResponse.builder().
                id(user.getId()).
                username(user.getUsername()).
                createdDate(user.getCreatedDate()).
                fullName(user.getFullName()).
                gender(user.getGender()).
                phoneNumber(user.getPhoneNumber()).
                role(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet())).
                city(user.getAddress().getCity()).
                district(user.getAddress().getDistrict()).
                wards(user.getAddress().getWards()).
                moreDetails(user.getAddress().getMoreDetails()).
                permission(ParseUtils.parseStringArrayToIntArray(user.getPermission())).
                isDeactivate(user.getIsDeactive()).
                build()));
        return response;
    }


    @Override
    public ChangeAssistantPassResponse changePassword(Object value, ChangePassRequest request) {
        if (value instanceof Long) {
            var accountToChangePass = userRepository.findById((Long) value).get();
            accountToChangePass.setEPassword(encoder.encode(request.getNewPassword()));
            return ChangeAssistantPassResponse.builder()
                    .newPassWord(request.getNewPassword())
                    .build();
        }
        throw new UsernameNotFoundException("Không tìm thấy account");
    }

    @Override
    public AddAssistantAccountResponse addNewAssistantAccount(RegisterRequest registerRequest) {
        var user = userService.singup(registerRequest);

        return AddAssistantAccountResponse.builder().
                id(user.getId()).
                permission(registerRequest.getPermission()).
                username(user.getUsername()).
                password(registerRequest.getPassword()).
                createdDate(user.getCreatedDate()).
                role(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet())).
                city(registerRequest.getCity()).
                district(registerRequest.getDistrict()).
                wards(registerRequest.getWards()).
                moreDetails(registerRequest.getMoreDetails()).
                build();
    }

    @Override
    public List<StaffAccountResponse> getAllListStaffAccount() {
        return buildAssistantAccountByFilter(userRepository.findAllByOrderByCreatedDateDesc());
    }

    @Override
    @SneakyThrows
    public StaffAccountResponse getStaffAccountById(Long id) {
        var staffAcc = userRepository.findById(id).orElseThrow(() -> new ManagerException("Không tìm thấy account"));
        List<User> users = Collections.singletonList(staffAcc);
        return buildAssistantAccountByFilter(users).get(0);
    }

    public Set<Role> roleChecker(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(ERole.ROLE_STAFF)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
            }
        });
        return roles;
    }
}
