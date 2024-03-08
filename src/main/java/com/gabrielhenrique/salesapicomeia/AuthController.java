package com.gabrielhenrique.salesapicomeia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    @Tag(name = "Autenticação", description = "Realizar autenticação para usar a api")
    @Operation(summary = "Faz o cahamdo ao keycloak", description = "Realiza a autenticação e retorna o token")
    public ResponseEntity<Map> login(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest.getUsername(), authRequest.getPassword());
    }

    @Data
    public static class AuthRequest {
        private String username;
        private String password;

    }
}
