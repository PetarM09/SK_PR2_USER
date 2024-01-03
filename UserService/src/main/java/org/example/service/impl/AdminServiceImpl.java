package org.example.service.impl;

import org.example.repository.ZabraneRepository;
import org.example.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private final ZabraneRepository zabraneRepository;

    public AdminServiceImpl(ZabraneRepository zabraneRepository) {
        this.zabraneRepository = zabraneRepository;
    }

    @Override
    public void zabraniPristup(Long korisnikId) {
        zabraneRepository.updateZabranaStatus(korisnikId, true);
    }

    @Override
    public void odobriPristup(Long korisnikId) {
        zabraneRepository.updateZabranaStatus(korisnikId, false);
    }
}
