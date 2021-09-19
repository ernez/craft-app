package com.ernez.craftapp.service;

import com.ernez.craftapp.domain.AppUser;
import com.ernez.craftapp.dto.request.LoginRequest;
import com.ernez.craftapp.dto.response.JwtResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService {
    String signUpUser(AppUser appUser);
    JwtResponse signIn(LoginRequest loginRequest);
    void enableAppUser(String email);
    void disable(Long id);
    void delete(Long id);

    List<AppUser> findAllActive();
    AppUser create(AppUser appUser);
    void update(AppUser appUser);
}
