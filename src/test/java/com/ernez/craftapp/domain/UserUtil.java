package com.ernez.craftapp.domain;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    public static AppUser createDummyUser(Long id, String username, String firstName, String lastName) {
        AppUser appUser = AppUser.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        appUser.setId(id);
        return appUser;
    }

    public static List<AppUser> createDummyUserList() {
        List<AppUser> users = new ArrayList<>();
        users.add(createDummyUser(1L, "ernezcatovic", "Ernez", "Catovic"));
        users.add(createDummyUser(2L, "enacatovic","Ena", "Catovic"));
        return users;
    }
}
