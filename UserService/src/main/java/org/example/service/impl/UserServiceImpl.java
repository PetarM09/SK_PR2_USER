package org.example.service.impl;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javassist.NotFoundException;
import org.example.domain.Klijent;
import org.example.domain.Korisnici;
import org.example.dto.*;
import org.example.mapper.KorisnikMapper;
import org.example.repository.KlijentRepository;
import org.example.repository.KorisniciRepository;
import org.example.security.service.TokenService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private KlijentRepository klijentRepository;
    private KorisnikMapper userMapper;

//    public UserServiceImpl(TokenService tokenService, KorisniciRepository userRepository, KlijentRepository klijentRepository, KorisnikMapper userMapper) {
//        this.tokenService = tokenService;
//        this.userRepository = userRepository;
//        this.klijentRepository = klijentRepository;
//        this.userMapper = userMapper;
//    }

    public UserServiceImpl(KorisniciRepository userRepository, TokenService tokenService, KorisnikMapper userMapper) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }

    @Override
    public Page<KorisniciDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    public KorisniciDto add(KorisniciCreateDto userCreateDto) {
        Korisnici user = userMapper.userCreateDtoToUser(userCreateDto);
        userRepository.save(user);
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

    public Optional<Korisnici> findKorisnikByID(Integer id){
        return userRepository.findById(id);
    }
    public Optional<Klijent> findKlijentByID(Integer id){
        return klijentRepository.findById(id);
    }

}
