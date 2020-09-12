package com.ernez.craftapp.repository;

import com.ernez.craftapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEnabled(Boolean enabled);
}
