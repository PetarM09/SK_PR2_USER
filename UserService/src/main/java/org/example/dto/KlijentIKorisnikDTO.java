package org.example.dto;

import org.example.domain.Klijent;
import org.example.domain.Korisnici;
import org.example.repository.KlijentRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Optional;

public class KlijentIKorisnikDTO {

    private Integer id;

    private String email;

    private LocalDate datumRodjenja;

    private String ime;

    private String prezime;

    private String clanskaKarta;
    private Integer zakazaniTreninzi;

    public KlijentIKorisnikDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getClanskaKarta() {
        return clanskaKarta;
    }

    public void setClanskaKarta(String clanskaKarta) {
        this.clanskaKarta = clanskaKarta;
    }

    public Integer getZakazaniTreninzi() {
        return zakazaniTreninzi;
    }

    public void setZakazaniTreninzi(Integer zakazaniTreninzi) {
        this.zakazaniTreninzi = zakazaniTreninzi;
    }
}
