package org.example.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("M")
public class Menadzer extends Korisnici {
    @OneToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnici korisnik;

    @Size(max = 255)
    @NotNull
    @Column(name = "sala_naziv", nullable = false)
    private String salaNaziv;

    @NotNull
    @Column(name = "datum_zaposljavanja", nullable = false)
    private LocalDate datumZaposljavanja;


    public Korisnici getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnici korisnik) {
        this.korisnik = korisnik;
    }

    public String getSalaNaziv() {
        return salaNaziv;
    }

    public void setSalaNaziv(String salaNaziv) {
        this.salaNaziv = salaNaziv;
    }

    public LocalDate getDatumZaposljavanja() {
        return datumZaposljavanja;
    }

    public void setDatumZaposljavanja(LocalDate datumZaposljavanja) {
        this.datumZaposljavanja = datumZaposljavanja;
    }
}
