package com.gabrielhenrique.salesapicomeia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Realizar autenticação para usar a api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    @Operation(summary = "Realiza Autenticação", description = "Realiza a autenticação e retorna o token")
    public ResponseEntity<Map> login(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest.getUsername(), authRequest.getPassword());
    }

    @Data
    public static class AuthRequest {

        @Schema(example = "gabriel")
        private String username;
        @Schema(example = "123456")
        private String password;

    }
}
