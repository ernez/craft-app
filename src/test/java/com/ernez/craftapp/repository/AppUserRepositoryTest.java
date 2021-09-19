package com.ernez.craftapp.repository;

import com.ernez.craftapp.domain.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class AppUserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserRepository appUserRepository;

    private AppUser appUser;

    @BeforeEach
    void setUp() {
        appUser = AppUser.builder()
            .username("ernezcatovic")
            .password("pass")
            .firstName("Ernez")
            .lastName("Catovic")
            .email("email")
            .build();
    }

    @Test
    void existsByEmail() {
        //given
        entityManager.persist(appUser);
        entityManager.flush();
        //when
        boolean userExists = appUserRepository.existsByEmail("email");
        //then
        assertEquals(true, userExists);
    }

    @Test
    void findByUsernameOrEmail() {
        //given
        entityManager.persist(appUser);
        entityManager.flush();
        //when
        Optional<AppUser> appUserResult = appUserRepository.findByUsernameOrEmail("ernezcatovic", "some@email");
        //then
        assertEquals(appUser, appUserResult.get());
    }

    @Test
    void enableAppUser() {
        //given
        appUser.setEnabled(false);
        String email = "email";
        entityManager.persist(appUser);
        entityManager.flush();
        //when
        appUserRepository.enableAppUser(email);
        //then
        assertEquals(true, appUserRepository.findByEmail(email).get().getEnabled());
    }

}
