package com.api.vetgroup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@ResponseBody
public class JwtExpiredException extends AuthenticationException {
    private static final long serialVersionUID = 1L;
    public JwtExpiredException(String ex) {
        super(ex);
    }
}
