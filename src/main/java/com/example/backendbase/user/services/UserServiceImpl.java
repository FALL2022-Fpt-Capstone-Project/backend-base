package com.example.backendbase.user.services;

import com.example.backendbase.security.service.UserAuthenDetailsImpl;
import com.example.backendbase.user.entity.LoginResponse;
import com.example.backendbase.user.repo.RoleRepo;
import com.example.backendbase.user.repo.UserRepo;
import com.example.backendbase.security.enums.ERole;
import com.example.backendbase.security.util.JwtUtils;
import com.example.backendbase.user.entity.Role;
import com.example.backendbase.user.entity.User;
import com.example.backendbase.user.entity.request.LoginRequest;
import com.example.backendbase.user.entity.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepository;

    @Autowired
    RoleRepo roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @Override
    public LoginResponse signin(LoginRequest loginRequest) {
        jwtUtils.getCleanJwtCookie();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserAuthenDetailsImpl userDetails = (UserAuthenDetailsImpl) authentication.getPrincipal();

        return LoginResponse.builder()
                .token(jwtUtils.generateJwtCookie(userDetails).getValue())
                .role(authentication.getAuthorities().stream()
                        .map(r -> r.getAuthority()).collect(Collectors.toSet()))
                .build();
    }

    @Override
    @SneakyThrows
    public Object singup(RegisterRequest signUpRequest) {
        if (userRepository.findByUsername(signUpRequest.getUserName()).isPresent()) {
            throw new Exception("Error: Username existed!!");
        }

        // Create new user's account
        User user = User.builder().
                username(signUpRequest.getUserName()).
                ePassword(encoder.encode(signUpRequest.getPassword())).
                userInforId(signUpRequest.getUserInforId()).
                identityId(signUpRequest.getIdentityId()).
                createdDate(new Timestamp(System.currentTimeMillis())).
                build();

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();


        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                case "mod":
                    Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return user;
    }

    public String logout() {
        jwtUtils.getCleanJwtCookie();
        return "You've been signed out!";
    }
}
