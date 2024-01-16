package org.example.service;


import javassist.NotFoundException;
import org.example.domain.Klijent;
import org.example.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<KorisniciDto> findAll(Pageable pageable);

    KorisniciDto add(KorisniciCreateDto userCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException;

    boolean proveriZabranu(Integer idKorisnika);

    KorisnikKlijentDTO getUser(Integer id);
    String verifikujKorisnika(String kod);
    KorisniciDto findClientById(Long id);
}
