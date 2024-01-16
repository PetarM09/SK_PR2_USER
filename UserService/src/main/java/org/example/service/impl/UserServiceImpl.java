package org.example.service.impl;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javassist.NotFoundException;
import org.example.domain.Klijent;
import org.example.domain.Korisnici;
import org.example.domain.Zabrane;
import org.example.dto.*;
import org.example.mapper.KorisnikMapper;
import org.example.repository.KlijentRepository;
import org.example.repository.KorisniciRepository;
import org.example.repository.MenadzerRepository;
import org.example.repository.ZabraneRepository;
import org.example.security.service.TokenService;
import org.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager entityManager;
    private TokenService tokenService;
    private KorisniciRepository userRepository;
    private KorisnikMapper userMapper;
    private KlijentRepository klijentRepository;
    private MenadzerRepository menadzerRepository;
    private final ZabraneRepository zabraneRepository;

    public UserServiceImpl(EntityManager entityManager, TokenService tokenService, KorisniciRepository userRepository, KorisnikMapper userMapper, KlijentRepository klijentRepository, MenadzerRepository menadzerRepository,
                           ZabraneRepository zabraneRepository) {
        this.entityManager = entityManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.klijentRepository = klijentRepository;
        this.menadzerRepository = menadzerRepository;
        this.zabraneRepository = zabraneRepository;
    }

    @Override
    public Page<KorisniciDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    public KorisniciDto add(KorisniciCreateDto userCreateDto) {
        Korisnici user = userMapper.userCreateDtoToUser(userCreateDto);
        Zabrane zabrane = new Zabrane();
        zabrane.setKorisnik(user);
        zabrane.setKorisnikId(user.getId());
        zabrane.setZabranjen(false);
        user.setZabrane(zabrane);


        userRepository.save(user);
        zabraneRepository.save(zabrane);

        return userMapper.userToUserDto(user);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) throws NotFoundException {
        //Try to find active user for specified credentials
        Korisnici user = userRepository
                .findByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with email: %s and password: %s not found.", tokenRequestDto.getEmail(),
                                tokenRequestDto.getPassword())));


        if(proveriZabranu(user.getId())){
            return new TokenResponseDto("Zabranjen pristup");
        }
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("tip_korisnika", user.getTipKorisnika().getNaziv());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }
    @Override
    public boolean proveriZabranu(Integer korisnik_id) {
        try {
            String jpql = "SELECT z.zabranjen FROM Zabrane z WHERE z.korisnikId = :korisnik_id";
            Boolean zabranjenValue = entityManager.createQuery(jpql, Boolean.class)
                    .setParameter("korisnik_id", korisnik_id)
                    .getSingleResult();
            return zabranjenValue;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public KorisnikKlijentDTO getUser(Integer id) {
        Klijent klijent = klijentRepository.findByKorisnikId(id).orElse(null);
        Korisnici korisnik = userRepository.findById(id).orElse(null);
        KorisnikKlijentDTO korisnikKlijentDTO = new KorisnikKlijentDTO();
        korisnikKlijentDTO.setEmail(korisnik.getEmail());
        korisnikKlijentDTO.setIme(korisnik.getIme());
        korisnikKlijentDTO.setPrezime(korisnik.getPrezime());
        korisnikKlijentDTO.setDatumRodjenja(korisnik.getDatumRodjenja());
        korisnikKlijentDTO.setUsername(korisnik.getUsername());
        korisnikKlijentDTO.setClanskaKarta(klijent.getClanskaKarta());
        korisnikKlijentDTO.setZakazaniTreninzi(klijent.getZakazaniTreninzi());
        korisnikKlijentDTO.setPassword(korisnik.getPassword());
        return korisnikKlijentDTO;

    }
}