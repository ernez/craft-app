package com.ernez.craftapp.repository;

import com.ernez.craftapp.domain.Firm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmRepository extends JpaRepository<Firm, Integer> {
}
