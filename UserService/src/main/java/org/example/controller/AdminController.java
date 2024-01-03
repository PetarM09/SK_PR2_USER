package org.example.controller;

import org.example.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/zabrani-pristup/{korisnikId}")
    public ResponseEntity<String> zabraniPristup(@PathVariable Long korisnikId) {
        adminService.zabraniPristup(korisnikId);
        return ResponseEntity.ok("Pristup je zabranjen.");
    }

    @PostMapping("/odobri-pristup/{korisnikId}")
    public ResponseEntity<String> odobriPristup(@PathVariable Long korisnikId) {
        adminService.odobriPristup(korisnikId);
        return ResponseEntity.ok("Pristup je odobren.");
    }
}
