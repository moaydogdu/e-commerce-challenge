package org.moaydogdu.enoca_challenge.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Map;
import java.util.Set;

public interface EnocaTokenService {
    EnocaToken generateToken(
            final Map<String, Object> claims
    );

    EnocaToken generateToken(
            final Map<String, Object> claims,
            final String refreshToken
    );

    UsernamePasswordAuthenticationToken getAuthentication(
            final String token
    );

    void verifyAndValidate(
            final String jwt
    );

    void verifyAndValidate(
            final Set<String> jwts
    );

    Jws<Claims> getClaims(
            final String jwt
    );

    Claims getPayload(
            final String jwt
    );

    String getId(
            final String jwt
    );
}
