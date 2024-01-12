package org.example.repository;

import org.example.domain.Klijent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KlijentRepository extends JpaRepository<Klijent, Integer> {
}
