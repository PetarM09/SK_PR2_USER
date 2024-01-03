package org.example.controller;

import javassist.NotFoundException;
import org.example.dto.KorisniciDto;
import org.example.service.ProfilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profil")
public class ProfilController {
    private final ProfilService profilService;

    public ProfilController(ProfilService profilService) {
        this.profilService = profilService;
    }

    @PutMapping()
    public ResponseEntity<KorisniciDto> azurirajProfil(
            @RequestBody @Valid KorisniciDto updateDto, Long korisnikId) throws NotFoundException {
        KorisniciDto azuriraniProfil = profilService.azurirajProfil(Math.toIntExact(korisnikId), updateDto);
        return new ResponseEntity<>(azuriraniProfil, HttpStatus.OK);
    }
}
