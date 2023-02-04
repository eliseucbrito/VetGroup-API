package com.api.vetgroup.services;

import com.api.vetgroup.dtos.security.AccountCredentialsDTO;
import com.api.vetgroup.dtos.security.TokenDTO;
import com.api.vetgroup.repositories.UserRepository;
import com.api.vetgroup.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsDTO data) {
        try {
            var userName = data.getUsername();
            var password = data.getPassword();
            var user = repository.findByUsername(userName);

            if (user == null) {
                throw new UsernameNotFoundException("Username "+userName+" not found!");
            }

            if (!user.getAccountNonExpired()) {
                throw new IllegalArgumentException("This account is expired!");
            }

            if (!user.getAccountNonLocked()) {
                throw new IllegalArgumentException("This account is locked!");
            }

            if (!user.getCredentialsNonExpired()) {
                throw new IllegalArgumentException("This account's credentials have expired!");
            }

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, password));

            var tokenResponse = new TokenDTO();
            tokenResponse = tokenProvider.createAccessToken(userName, user.getRoles());

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = repository.findByUsername(username);
        var tokenResponse = new TokenDTO();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username "+username+" not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }
}
