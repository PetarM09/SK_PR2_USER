package org.example.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link org.example.domain.Korisnici}
 */
public class KorisniciCreateDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(max = 255)
    private final String username;
    @NotNull
    @Size(max = 255)
    private final String password;
    @NotNull
    @Size(max = 255)
    private final String email;
    @NotNull
    private final LocalDate datumRodjenja;
    @NotNull
    @Size(max = 255)
    private final String ime;
    @NotNull
    @Size(max = 255)
    private final String prezime;
    private final Integer tipKorisnikaId;
    private final String tipKorisnikaNaziv;

    public KorisniciCreateDto(Integer id, String username, String password, String email, LocalDate datumRodjenja, String ime, String prezime, Integer tipKorisnikaId, String tipKorisnikaNaziv) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.datumRodjenja = datumRodjenja;
        this.ime = ime;
        this.prezime = prezime;
        this.tipKorisnikaId = tipKorisnikaId;
        this.tipKorisnikaNaziv = tipKorisnikaNaziv;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public Integer getTipKorisnikaId() {
        return tipKorisnikaId;
    }

    public String getTipKorisnikaNaziv() {
        return tipKorisnikaNaziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KorisniciCreateDto entity = (KorisniciCreateDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.username, entity.username) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.datumRodjenja, entity.datumRodjenja) &&
                Objects.equals(this.ime, entity.ime) &&
                Objects.equals(this.prezime, entity.prezime) &&
                Objects.equals(this.tipKorisnikaId, entity.tipKorisnikaId) &&
                Objects.equals(this.tipKorisnikaNaziv, entity.tipKorisnikaNaziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, datumRodjenja, ime, prezime, tipKorisnikaId, tipKorisnikaNaziv);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "username = " + username + ", " +
                "password = " + password + ", " +
                "email = " + email + ", " +
                "datumRodjenja = " + datumRodjenja + ", " +
                "ime = " + ime + ", " +
                "prezime = " + prezime + ", " +
                "tipKorisnikaId = " + tipKorisnikaId + ", " +
                "tipKorisnikaNaziv = " + tipKorisnikaNaziv + ")";
    }
}