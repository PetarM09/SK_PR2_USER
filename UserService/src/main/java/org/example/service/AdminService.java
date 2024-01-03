package org.example.service;

public interface AdminService {
    void zabraniPristup(Long korisnikId);

    void odobriPristup(Long korisnikId);
}
