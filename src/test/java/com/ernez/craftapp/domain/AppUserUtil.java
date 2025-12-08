package com.ernez.craftapp.domain;

import com.ernez.craftapp.domain.enumeration.ERole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppUserUtil {
    public static AppUser createDummyUser(Long id, String firstName, String lastName) {
        AppUser appUser = AppUser.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        appUser.setId(id);
        appUser.setRoles(getUserRoles());
        return appUser;
    }

    private static Set<Role> getUserRoles() {
        Role role = new Role();
        role.setName(ERole.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        return roles;
    }

    public static List<AppUser> createDummyUserList() {
        List<AppUser> users = new ArrayList<>();
        users.add(createDummyUser(1L, "Ernez", "Catovic"));
        users.add(createDummyUser(2L, "Ena", "Catovic"));
        return users;
    }
}
