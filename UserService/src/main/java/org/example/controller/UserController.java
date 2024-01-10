package org.example.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.example.domain.Klijent;
import org.example.domain.Korisnici;
import org.example.dto.KorisniciCreateDto;
import org.example.dto.KorisniciDto;
import org.example.dto.TokenRequestDto;
import org.example.dto.TokenResponseDto;
import org.example.security.CheckSecurity;
import org.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/korisnici")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get all users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<Page<KorisniciDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
                                                          Pageable pageable) {

        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }


    @ApiOperation(value = "Register user")
    @PostMapping("/register-user")
    public ResponseEntity<KorisniciDto> registerUser(@RequestBody @Valid KorisniciCreateDto userCreateDto) {
        userCreateDto.setTipKorisnikaNaziv("KLIJENT");
        return new ResponseEntity<>(userService.add(userCreateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Register user")
    @PostMapping("/register-manager")
    public ResponseEntity<KorisniciDto> registerManager(@RequestBody @Valid KorisniciCreateDto userCreateDto) {
        userCreateDto.setTipKorisnikaNaziv("MENADZER");
        return new ResponseEntity<>(userService.add(userCreateDto), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) throws NotFoundException {
        TokenResponseDto tokenResponseDto = userService.login(tokenRequestDto);
        if (tokenResponseDto.getToken().equals("Zabranjen pristup"))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(tokenResponseDto, HttpStatus.OK);
    }

    @GetMapping("/brojTreninga/{id}")
    public ResponseEntity<Integer> brojTreninga(@RequestBody Integer id) throws NotFoundException {
        Optional<Klijent> klijent = userService.findById(id);

        return new ResponseEntity<>(klijent.get().getZakazaniTreninzi(), HttpStatus.OK);
    }
}
