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

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class AppUserRepositoryTest {
    private static final String EMAIL = "ernez.catovic@gmail.com";
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserRepository appUserRepository;

    private AppUser appUser;

    @BeforeEach
    void setUp() {
        appUser = AppUser.builder()
            .password("pass")
            .firstName("Ernez")
            .lastName("Catovic")
            .email(EMAIL)
            .build();
    }

    @Test
    void existsByEmail() {
        //given
        entityManager.persist(appUser);
        entityManager.flush();
        //when
        boolean userExists = appUserRepository.existsByEmail(EMAIL);
        //then
        assertEquals(true, userExists);
    }

    @Test
    void findByCorrectEmail() {
        //given
        entityManager.persist(appUser);
        entityManager.flush();
        //when
        Optional<AppUser> appUserResult = appUserRepository.findByEmail(EMAIL);
        //then
        assertEquals(appUser, appUserResult.get());
    }

    @Test
    void couldNotFindByIncorrectEmail() {
        //given
        entityManager.persist(appUser);
        entityManager.flush();
        //when
        Optional<AppUser> appUserResult = appUserRepository.findByEmail("incorrectEmail");
        //then
        assertTrue(!appUserResult.isPresent());
    }

    @Test
    void enableAppUser() {
        //given
        appUser.setEnabled(false);
        String email = EMAIL;
        entityManager.persist(appUser);
        entityManager.flush();
        //when
        appUserRepository.enableAppUser(email);
        //then
        assertEquals(true, appUserRepository.findByEmail(email).get().getEnabled());
    }
}
