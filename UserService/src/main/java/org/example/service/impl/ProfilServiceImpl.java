package org.example.service.impl;

import javassist.NotFoundException;
import org.example.domain.Korisnici;
import org.example.dto.KorisniciDto;
import org.example.repository.KorisniciRepository;
import org.example.service.ProfilService;
import org.springframework.stereotype.Service;

@Service
public class ProfilServiceImpl implements ProfilService {
    private final KorisniciRepository korisniciRepository;

    public ProfilServiceImpl(KorisniciRepository korisniciRepository) {
        this.korisniciRepository = korisniciRepository;
    }

    public KorisniciDto azurirajProfil(Integer korisnikId, KorisniciDto updateDto) throws NotFoundException {
        Korisnici korisnik = korisniciRepository.findById(korisnikId)
                .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen"));

        korisnik.setDatumRodjenja(updateDto.getDatumRodjenja());
        korisnik.setUsername(updateDto.getUsername());
        korisnik.setPassword(updateDto.getPassword());
        korisnik.setEmail(updateDto.getEmail());
        korisnik.setIme(updateDto.getIme());
        korisnik.setPrezime(updateDto.getPrezime());

        korisniciRepository.save(korisnik);

        KorisniciDto korisniciDto = new KorisniciDto();
        korisniciDto.setId(korisnik.getId());
        korisniciDto.setDatumRodjenja(korisnik.getDatumRodjenja());
        korisniciDto.setUsername(korisnik.getUsername());
        korisniciDto.setPassword(korisnik.getPassword());
        korisniciDto.setEmail(korisnik.getEmail());
        korisniciDto.setIme(korisnik.getIme());
        korisniciDto.setPrezime(korisnik.getPrezime());

        return korisniciDto;
    }
}

