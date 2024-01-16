package org.example.repository;

import org.example.domain.Menadzer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenadzerRepository extends JpaRepository<Menadzer, Integer> {
    Optional<Menadzer> findByKorisnik_id(Integer korisnik_id);
}
