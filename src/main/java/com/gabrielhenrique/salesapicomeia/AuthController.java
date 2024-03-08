package com.gabrielhenrique.salesapicomeia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map> login(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest.getUsername(), authRequest.getPassword());
    }

    @Data
    public static class AuthRequest {
        private String username;
        private String password;

    }
}
