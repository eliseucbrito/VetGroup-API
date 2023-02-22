package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.security.AccountCredentialsDTO;
import com.api.vetgroup.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsDTO data) {

        try {
            if (checkIfParamsIsNotNull(data))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

            var token = authService.signin(data);
            if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
            return token;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping(value = "/refresh")
    public ResponseEntity refreshToken(@RequestHeader("Authorization") String refreshToken)
    {
        if (checkIfParamsIsNotNull(refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        var token = authService.refreshToken(refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return token;
    }

    private static boolean checkIfParamsIsNotNull( String refreshToken) {
        return refreshToken == null || refreshToken.isBlank();
    }

    private static boolean checkIfParamsIsNotNull(AccountCredentialsDTO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
