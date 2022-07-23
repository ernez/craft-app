/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ernez.craftapp.web;

import com.ernez.craftapp.domain.AppUser;
import com.ernez.craftapp.domain.Role;
import com.ernez.craftapp.dto.UserDto;
import com.ernez.craftapp.exception.DaoException;
import com.ernez.craftapp.repository.AppUserRepository;
import com.ernez.craftapp.service.AppUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ernez.craftapp.web.utils.Web.API;

/**
 * @author n.lamouchi
 */
@AllArgsConstructor
@RestController
//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(API + "/users")
@Slf4j
public class UserController {
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

    @GetMapping
    public List<UserDto> findAll() {
        return this.appUserRepository.findAll().stream()
                .map((AppUser appUser) -> AppUser.mapToDto(appUser)).collect(Collectors.toList());
    }

    @GetMapping("/active")
    public List<UserDto> findAllActiveUsers() {
        log.debug("Request to get all Customers");
        return this.appUserRepository.findAllByEnabled(true)
                .stream()
                .map(AppUser::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return this.appUserRepository.findById(id)
                .map(u -> AppUser.mapToDto(u))
                .orElseThrow(() -> new IllegalArgumentException("The required User dows not exist"));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(() ->
                new Exception("The required user does not exist."));
        if(appUser.getRoles() == null || appUser.getRoles().isEmpty()) {
            throw new Exception("User must have at least a role set!");
        }
        AppUser updatedUser = modifyAppUser(appUser, userDto);

        appUserRepository.save(updatedUser);
    }

    private AppUser modifyAppUser(AppUser appUser, UserDto userDto) {
        appUser.setId(userDto.getId());
        appUser.setFirstName(userDto.getFirstName());
        appUser.setLastName(userDto.getLastName());
        appUser.setEmail(userDto.getEmail());
        appUser.setPhoneNumber(userDto.getPhoneNumber());
        return appUser;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.appUserService.delete(id);
    }

//    private UserDto mapToDto(AppUser appUser) {
//        if (appUser != null) {
//            return UserDto.builder()
//                    .id(appUser.getId())
//                    .firstName(appUser.getFirstName())
//                    .lastName(appUser.getLastName())
//                    .email(appUser.getEmail())
//                    .phoneNumber(appUser.getPhoneNumber())
//                    .enabled(appUser.isEnabled())
//                    .build();
//        }
//        throw new IllegalArgumentException();
//    }
}
