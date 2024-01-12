package org.example.service;


import javassist.NotFoundException;
import org.example.domain.Klijent;
import org.example.domain.Korisnici;
import org.example.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Page<KorisniciDto> findAll(Pageable pageable);

    KorisniciDto add(KorisniciCreateDto userCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException;

    boolean proveriZabranu(Integer idKorisnika);

    Optional<Korisnici> findKorisnikByID(Integer id);
    Optional<Klijent> findKlijentByID(Integer id);
}
