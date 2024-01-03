package org.example.repository;

import org.example.domain.Korisnici;
import org.example.domain.TipKorisnika;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KorisniciRepository extends JpaRepository<Korisnici, Integer> {
      Optional<Korisnici> findByUsernameAndPassword(String username, String password);
      Optional<Korisnici> findByEmailAndPassword(String email, String password);
}