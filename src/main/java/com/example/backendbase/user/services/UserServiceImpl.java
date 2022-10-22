package com.example.backendbase.user.services;

import com.example.backendbase.common.utils.ParseUtils;
import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.manager.entity.Address;
import com.example.backendbase.security.service.UserAuthenDetailsImpl;
import com.example.backendbase.manager.entity.request.ChangePassRequest;
import com.example.backendbase.manager.entity.response.AddAssistantAccountResponse;
import com.example.backendbase.manager.entity.response.ChangeAssistantPassResponse;
import com.example.backendbase.manager.entity.response.StaffAccountResponse;
import com.example.backendbase.user.entity.request.LoginResponse;
import com.example.backendbase.user.repo.RoleRepo;
import com.example.backendbase.user.repo.UserRepo;
import com.example.backendbase.security.enums.ERole;
import com.example.backendbase.security.util.JwtUtils;
import com.example.backendbase.user.entity.Role;
import com.example.backendbase.user.entity.User;
import com.example.backendbase.user.entity.request.LoginRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;
import com.example.backendbase.user.util.CurrentUserUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final AuthenticationManager authenticationManager;

    private final UserRepo userRepository;

    private final RoleRepo roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;


    @Override
    public LoginResponse signin(LoginRequest loginRequest) {
        jwtUtils.getCleanJwtCookie();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        var user = (UserAuthenDetailsImpl) authentication.getPrincipal();
        if (userRepository.findById(user.getId()).get().getIsDeactive()) {
            throw new AccountStatusException("This account was deactivate") {
            };
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserAuthenDetailsImpl userDetails = (UserAuthenDetailsImpl) authentication.getPrincipal();

        return LoginResponse.builder()
                .token(jwtUtils.generateJwtCookie(userDetails).getValue())
                .role(authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }

    @Override
    @SneakyThrows
    public User singup(RegisterRequest signUpRequest) {
        if (userRepository.findByUsername(signUpRequest.getUserName()).isPresent()) {
            throw new Exception("Error: Username existed!!");
        }

        // Create new user's account
        User user = User.builder().
                username(signUpRequest.getUserName()).
                ePassword(encoder.encode(signUpRequest.getPassword())).
                fullName(signUpRequest.getFullName()).
                gender(signUpRequest.getGender()).
                phoneNumber(signUpRequest.getPhoneNumber()).
                createdDate(TimeUtils.getCurrentTime()).
                permission(ParseUtils.parseIntArrayToString(signUpRequest.getPermission())).
                address(Address.builder().
                        city(signUpRequest.getCity()).
                        district(signUpRequest.getDistrict()).
                        wards(signUpRequest.getDistrict()).
                        moreDetails(signUpRequest.getMoreDetails()).
                        createdBy(CurrentUserUtils.getCurrentUser()).
                        updatedTime(TimeUtils.getCurrentTime()).
                        build()).
                build();
        user.setRoles(roleChecker(signUpRequest.getRoles()));
        userRepository.save(user);

        return user;
    }

    public String logout() {
        jwtUtils.getCleanJwtCookie();
        return "You've been signed out!";
    }


    @Override
    public List<StaffAccountResponse> getListUserByRole(ERole role) {
        return null;
    }

//    @Override
//    public List<ListAssistantAccountResponse> getListUserByRoleDeactive(ERole role, boolean isDeactive) {
////        return userRepository.findAllByRoles_NameAAndIsOwner(role, isDeactive);
//        // TO-DO :
//        return null;
//    }

    @Override
    public List<StaffAccountResponse> getListAssistantAccount() {
        var listAssistantAccount = userRepository.findAllByIsOwner(false);
        List<StaffAccountResponse> response = new ArrayList<>();
        listAssistantAccount.forEach(user -> {
            response.add(StaffAccountResponse.builder().
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
        throw new UsernameNotFoundException("Can not found username or id");
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
        throw new UsernameNotFoundException("Can not found username or id");
    }

    @Override
    public AddAssistantAccountResponse addNewAssistantAccount(RegisterRequest registerRequest) {
        var user = singup(registerRequest);

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

    public static void main(String[] args) {
        System.out.println(TimeUtils.getCurrentTime());
    }
}
