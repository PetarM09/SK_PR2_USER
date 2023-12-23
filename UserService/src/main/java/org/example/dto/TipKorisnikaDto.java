package org.example.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link org.example.domain.TipKorisnika}
 */
public class TipKorisnikaDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(max = 20)
    private final String naziv;

    public TipKorisnikaDto(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipKorisnikaDto entity = (TipKorisnikaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.naziv, entity.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "naziv = " + naziv + ")";
    }
}