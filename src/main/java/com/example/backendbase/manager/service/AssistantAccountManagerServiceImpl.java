package com.example.backendbase.manager.service;

import com.example.backendbase.common.utils.ParseUtils;
import com.example.backendbase.manager.constant.ManagerConstant;
import com.example.backendbase.security.enums.ERole;
import com.example.backendbase.security.util.JwtUtils;
import com.example.backendbase.user.entity.Role;
import com.example.backendbase.user.entity.User;
import com.example.backendbase.manager.entity.request.ChangePassRequest;
import com.example.backendbase.manager.entity.request.ModifyAssistantAccountRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;
import com.example.backendbase.manager.entity.response.AddAssistantAccountResponse;
import com.example.backendbase.manager.entity.response.ChangeAssistantPassResponse;
import com.example.backendbase.manager.entity.response.ListAssistantAccountResponse;
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
public class AssistantAccountManagerServiceImpl implements AssistantAccountManagerService {

    private final UserRepo userRepository;

    private final RoleRepo roleRepository;

    private final PasswordEncoder encoder;

    private final IUserService userService;

    @Override
    @SneakyThrows
    public User updateAccount(ModifyAssistantAccountRequest changeRequest) {
        Optional<User> userToUpdateRole;
        if (changeRequest.getId() == null) {
            userToUpdateRole = userRepository.findByUsername(changeRequest.getUserName());
            if (!userToUpdateRole.isPresent()) throw new UsernameNotFoundException("Username Not Found!!");
        } else {
            changeRequest.setId(changeRequest.getId());
            userToUpdateRole = userRepository.findById(changeRequest.getId());
            if (!userToUpdateRole.isPresent()) throw new UsernameNotFoundException("AccountId Not Found!!");
        }

        userToUpdateRole.get().setUsername(changeRequest.getUserName());
        userToUpdateRole.get().setGender(changeRequest.getGender());
        userToUpdateRole.get().setFullName(changeRequest.getFullName());
        userToUpdateRole.get().setPhoneNumber(changeRequest.getPhoneNumber());
        userToUpdateRole.get().getAddress().setMoreDetails(changeRequest.getMoreDetails());
        if (!Objects.isNull(changeRequest.getRole())) {
            userToUpdateRole.get().setRoles(roleChecker(changeRequest.getRole()));

        }
        return userRepository.save(userToUpdateRole.get());

    }

//    @Override
//    public List<ListAssistantAccountResponse> getListUserByRoleDeactive(ERole role, boolean isDeactive) {
////        return userRepository.findAllByRoles_NameAAndIsOwner(role, isDeactive);
//        // TO-DO :
//        return null;
//    }

    @Override
    public List<ListAssistantAccountResponse> getListAssistantAccount(String condition, String roles, int deactivate) {
        List<ERole> listRole = new ArrayList<>();

        if (roles.equals("all")) {
            listRole = Arrays.stream(ERole.class.getEnumConstants()).collect(Collectors.toList());
        } else {
            if (roles.equals("admin")) {
                listRole.add(ERole.ROLE_ADMIN);
            }
            if (roles.equals("staff")) {
                listRole.add(ERole.ROLE_STAFF);
            }
        }

        //activate == 1 => true
        boolean isDeactivate = true;
        if (deactivate == ManagerConstant.ACTIVATE_ACCOUNT)
            isDeactivate = false;

        switch (condition) {
            case "latest":
                return buildListAssistantAccountByFilter(
                        userRepository.findAllByIsOwnerAndRoles_NameInAndIsDeactiveOrderByCreatedDateDesc(false, listRole, isDeactivate));
            case "oldest":
                return buildListAssistantAccountByFilter(
                        userRepository.findAllByIsOwnerAndRoles_NameInAndIsDeactiveOrderByCreatedDateAsc(false, listRole, isDeactivate));
            case "all":
                return buildListAssistantAccountByFilter(
                        userRepository.findAllByIsOwnerAndRoles_NameInAndIsDeactive(false, listRole, isDeactivate));
        }
        return buildListAssistantAccountByFilter(userRepository.findAllByIsOwner(false));
    }

    public List<ListAssistantAccountResponse> buildListAssistantAccountByFilter(List<User> listAssistantAccount) {
        List<ListAssistantAccountResponse> response = new ArrayList<>();
        listAssistantAccount.forEach(user -> {
            response.add(ListAssistantAccountResponse.builder().
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
                    build());
        });
        return response;
    }

    @Override
    public String deactiveAssistantAccount(Object value) {
        if (value instanceof Long) {
            var userToDeactive = userRepository.findById((Long) value).get();
            String username = userToDeactive.getUsername();
            userToDeactive.setIsDeactive(true);
            userRepository.save(userToDeactive);
            return "Deactive " + username + " success";
        }
        if (value instanceof String) {
            var userToDeactive = userRepository.deleteUserByUsername((String) value);
            userToDeactive.setIsDeactive(true);
            return "Deactive " + value + " success";
        }
        throw new UsernameNotFoundException("Can not found username or id");
    }

    @Override
    public ChangeAssistantPassResponse changePassword(Object value, ChangePassRequest request) {
        if (value instanceof Long) {
            var accountToChangePass = userRepository.findById((Long) value).get();
            accountToChangePass.setEPassword(encoder.encode(request.getNewPassword()));
            return ChangeAssistantPassResponse.builder()
                    .newPassWord(request.getNewPassword())
                    .account(accountToChangePass.getUsername())
                    .build();
        }
        if (value instanceof String) {
            var accountToChangePass = userRepository.findByUsername((String) value).get();
            accountToChangePass.setEPassword(encoder.encode(request.getNewPassword()));
            return ChangeAssistantPassResponse.builder()
                    .newPassWord(request.getNewPassword())
                    .account(accountToChangePass.getUsername())
                    .build();
        }
        throw new UsernameNotFoundException("Can not found username or id");
    }

    @Override
    public AddAssistantAccountResponse addNewAssistantAccount(RegisterRequest registerRequest) {
        var user = userService.singup(registerRequest);

        return AddAssistantAccountResponse.builder().
                id(user.getId()).
                username(user.getUsername()).
                createdDate(user.getCreatedDate()).
                role(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet())).
                city(registerRequest.getCity()).
                district(registerRequest.getDistrict()).
                wards(registerRequest.getWards()).
                moreDetails(registerRequest.getMoreDetails()).
                build();
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
