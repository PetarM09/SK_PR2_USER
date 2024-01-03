package org.example.repository;

import org.example.domain.Zabrane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ZabraneRepository extends JpaRepository<Zabrane, Long> {
    @Modifying
    @Query("UPDATE Zabrane z SET z.zabranjen = :zabranjen WHERE z.korisnikId = :korisnikId")
    void updateZabranaStatus(@Param("korisnikId") Long korisnikId, @Param("zabranjen") boolean zabranjen);
}

