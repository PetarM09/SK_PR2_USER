package org.example.service;


import javassist.NotFoundException;
import org.example.dto.KorisniciCreateDto;
import org.example.dto.KorisniciDto;
import org.example.dto.TokenRequestDto;
import org.example.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<KorisniciDto> findAll(Pageable pageable);

    KorisniciDto add(KorisniciCreateDto userCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException;
}
