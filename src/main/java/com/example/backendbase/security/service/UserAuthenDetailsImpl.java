package com.example.backendbase.security.service;

import com.example.backendbase.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserAuthenDetailsImpl extends User implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Collection<? extends GrantedAuthority> authorities;

    public UserAuthenDetailsImpl(Long id, String username, String password,
                                 Collection<? extends GrantedAuthority> authorities) {
        super(id, username, password);
        this.authorities = authorities;
    }

    public static UserAuthenDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserAuthenDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getEPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
