package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.example.domain.Klijent;
import org.example.dto.*;
import org.example.security.CheckSecurity;
import org.example.security.service.TokenService;
import org.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/korisnici")
public class UserController {

    private UserService userService;
    private TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
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
    public ResponseEntity<KorisniciDto> registerUser(@RequestBody String jsonRequestBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(jsonRequestBody);
            KorisniciCreateDto korisniciCreateDto = mapper.readValue(jsonRequestBody, KorisniciCreateDto.class);
            return new ResponseEntity<>(userService.add(korisniciCreateDto), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Register user")
    @PostMapping("/register-manager")
    public ResponseEntity<KorisniciDto> registerManager(@RequestBody String jsonRequestBody) {
        System.out.println("ovde baki");
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(jsonRequestBody);
            KorisniciCreateDto korisniciCreateDto = mapper.readValue(jsonRequestBody, KorisniciCreateDto.class);
            return new ResponseEntity<>(userService.add(korisniciCreateDto), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) throws NotFoundException {
        TokenResponseDto tokenResponseDto = userService.login(tokenRequestDto);
        if (tokenResponseDto.getToken().equals("Zabranjen pristup"))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(tokenResponseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "getUser")
    @GetMapping("/getUser/{id}")
    public ResponseEntity<KorisnikKlijentDTO> getUser(@PathVariable Integer id) throws NotFoundException {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @ApiOperation(value = "getUserID")
    @GetMapping("/getUserID")
    public ResponseEntity<Long> getUserID(@RequestHeader String authorization) throws NotFoundException {
        Long id = tokenService.parseId(authorization);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @ApiOperation(value = "getUserRole")
    @GetMapping("/getUserRole")
    public ResponseEntity<String> getUserRole(@RequestHeader String authorization) throws NotFoundException {
        System.out.println(authorization);
        String role = tokenService.parseRole(authorization);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("/verifikuj/{kod}")
    public ResponseEntity<String> verifyUser(@PathVariable("kod") String kod) {
        return new ResponseEntity<>(userService.verifikujKorisnika(kod), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KorisniciDto> getByIdNoToken(@PathVariable("id") Long id,@RequestHeader String authorization) {
        return new ResponseEntity<>(userService.findClientById(id), HttpStatus.OK);
    }
}
