package org.example.mapper;

import org.example.domain.Klijent;
import org.example.domain.Korisnici;
import org.example.domain.Menadzer;
import org.example.dto.KorisniciCreateDto;
import org.example.dto.KorisniciDto;
import org.example.dto.MenadzerDTO;
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
        userDto.setPassword(user.getPassword());
        userDto.setDatumRodjenja(user.getDatumRodjenja());
        return userDto;
    }

    public Korisnici userCreateDtoToUser(KorisniciCreateDto userCreateDto) {
        Korisnici user = new Korisnici();
        user.setId(3);
        user.setEmail(userCreateDto.getEmail());
        user.setIme(userCreateDto.getIme());
        user.setPrezime(userCreateDto.getPrezime());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setTipKorisnika(roleRepository.findByNaziv(userCreateDto.getTipKorisnikaNaziv()).get());

        if ("KLIJENT".equals(userCreateDto.getTipKorisnikaNaziv())) {
            if (user.getKlijent() == null) {
                user.setKlijent(new Klijent());
            }
            KorisniciCreateDto.KlijentCreateDto klijentDto = (KorisniciCreateDto.KlijentCreateDto) userCreateDto;
            user.getKlijent().setClanskaKarta(klijentDto.getClanskaKarta());
            user.getKlijent().setZakazaniTreninzi(klijentDto.getBrojZakazanihTrenutnih());

        } else if ("MENADZER".equals(userCreateDto.getTipKorisnikaNaziv())) {
            if (user.getMenadzer() == null) {
                user.setMenadzer(new Menadzer());
            }
            KorisniciCreateDto.MenadzerCreateDto menadzerDto = (KorisniciCreateDto.MenadzerCreateDto) userCreateDto;
            user.getMenadzer().setSalaNaziv(menadzerDto.getSalaNaziv());
            user.getMenadzer().setDatumZaposljavanja(menadzerDto.getDatumZaposljavanja());
        }
        return user;
    }
}
