package com.example.backendbase.security.service;

import com.example.backendbase.user.repo.UserRepo;
import com.example.backendbase.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenDetailsService implements IUserDetailsAuth{
    @Autowired
    UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserAuthenDetailsImpl.build(user);
    }
}
