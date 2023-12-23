package org.example.mapper;

import org.example.domain.Korisnici;
import org.example.dto.KorisniciCreateDto;
import org.example.dto.KorisniciDto;
import org.example.repository.TipKorisnikaRepository;
import org.springframework.stereotype.Component;

@Component
public class KorisnikMapper {

    private TipKorisnikaRepository roleRepository;

    public KorisnikMapper(TipKorisnikaRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public KorisniciDto userToUserDto(Korisnici user) {
        KorisniciDto userDto = new KorisniciDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setIme(user.getIme());
        userDto.setPrezime(user.getPrezime());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public Korisnici userCreateDtoToUser(KorisniciCreateDto userCreateDto) {
        Korisnici user = new Korisnici();
        user.setEmail(userCreateDto.getEmail());
        user.setIme(userCreateDto.getIme());
        user.setPrezime(userCreateDto.getPrezime());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setTipKorisnika(roleRepository.findByNaziv("TipKorisnika_Korisnici").get());

        return user;
    }
}
