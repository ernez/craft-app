package com.ernez.craftapp.repository;

import com.ernez.craftapp.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findAllByEnabled(Boolean enabled);

    Optional<AppUser> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Optional<AppUser> findByUsernameOrEmail(String username, String email);

    @Modifying(clearAutomatically = true)
    @Query("update AppUser a " +
            "set a.enabled = true where a.email = :email")
    void enableAppUser(@Param("email") String email);

}
