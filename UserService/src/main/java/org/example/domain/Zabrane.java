package org.example.domain;

import javax.persistence.*;

@Entity
@Table(name = "zabrane")
public class Zabrane {
    @Id
    @Column(name = "korisnik_id", nullable = false)
    private Long korisnikId;

    @OneToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnici korisnici;

    @Column(name = "zabranjen", nullable = false)
    private boolean zabranjen;

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Korisnici getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(Korisnici korisnici) {
        this.korisnici = korisnici;
    }

    public boolean isZabranjen() {
        return zabranjen;
    }

    public void setZabranjen(boolean zabranjen) {
        this.zabranjen = zabranjen;
    }
}
