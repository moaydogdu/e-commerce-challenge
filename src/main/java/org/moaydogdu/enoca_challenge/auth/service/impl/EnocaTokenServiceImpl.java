package org.moaydogdu.enoca_challenge.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.moaydogdu.enoca_challenge.auth.config.EnocaTokenConfigurationParameter;
import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.enums.EnocaConfigurationParameter;
import org.moaydogdu.enoca_challenge.auth.model.enums.EnocaTokenClaims;
import org.moaydogdu.enoca_challenge.auth.model.enums.TokenType;
import org.moaydogdu.enoca_challenge.auth.model.enums.UserType;
import org.moaydogdu.enoca_challenge.auth.service.EnocaInvalidTokenService;
import org.moaydogdu.enoca_challenge.auth.service.EnocaTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnocaTokenServiceImpl implements EnocaTokenService {

    private final EnocaTokenConfigurationParameter enocaTokenConfigurationParameter;
    private final EnocaInvalidTokenService enocaInvalidTokenService;

    public EnocaToken generateToken(
            final Map<String, Object> claims
    ) {
        final long currentTimeMillis = System.currentTimeMillis();

        final Date tokenIssuedAt = new Date(currentTimeMillis);

        final Date accessTokenExpiresAt = DateUtils.addMinutes(
                new Date(currentTimeMillis),
                enocaTokenConfigurationParameter.getAccessTokenExpireMinute()
        );

        final String accessToken = Jwts.builder()
                .header()
                .type(TokenType.BEARER.getValue())
                .and()
                .id(UUID.randomUUID().toString())
                .issuer(EnocaConfigurationParameter.ENOCA_ISSUER.getDefaultValue())
                .issuedAt(tokenIssuedAt)
                .expiration(accessTokenExpiresAt)
                .signWith(enocaTokenConfigurationParameter.getPrivateKey())
                .claims(claims)
                .compact();

        final Date refreshTokenExpiresAt = DateUtils.addDays(
                new Date(currentTimeMillis),
                enocaTokenConfigurationParameter.getRefreshTokenExpireDay()
        );

        final String refreshToken = Jwts.builder()
                .header()
                .type(TokenType.BEARER.getValue())
                .and()
                .id(UUID.randomUUID().toString())
                .issuer(enocaTokenConfigurationParameter.getIssuer())
                .issuedAt(tokenIssuedAt)
                .expiration(refreshTokenExpiresAt)
                .signWith(enocaTokenConfigurationParameter.getPrivateKey())
                .claim(EnocaTokenClaims.USER_ID.getValue(), claims.get(EnocaTokenClaims.USER_ID.getValue()))
                .compact();

        return EnocaToken.builder()
                .accessToken(accessToken)
                .accessTokenExpiresAt(accessTokenExpiresAt.toInstant().getEpochSecond())
                .refreshToken(refreshToken)
                .build();
    }

    public EnocaToken generateToken(
            final Map<String, Object> claims,
            final String refreshToken
    ) {
        final long currentTimeMillis = System.currentTimeMillis();

        final String refreshTokenId = this.getId(refreshToken);

        enocaInvalidTokenService.checkForInvalidityOfToken(refreshTokenId);

        final Date accessTokenIssuedAt = new Date(currentTimeMillis);

        final Date accessTokenExpiresAt = DateUtils.addMinutes(
                new Date(currentTimeMillis),
                enocaTokenConfigurationParameter.getAccessTokenExpireMinute()
        );

        final String accessToken = Jwts.builder()
                .header()
                .type(TokenType.BEARER.getValue())
                .and()
                .id(UUID.randomUUID().toString())
                .issuer(enocaTokenConfigurationParameter.getIssuer())
                .issuedAt(accessTokenIssuedAt)
                .expiration(accessTokenExpiresAt)
                .signWith(enocaTokenConfigurationParameter.getPrivateKey())
                .claims(claims)
                .compact();

        return EnocaToken.builder()
                .accessToken(accessToken)
                .accessTokenExpiresAt(accessTokenExpiresAt.toInstant().getEpochSecond())
                .refreshToken(refreshToken)
                .build();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(
            final String token
    ) {
        final Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(enocaTokenConfigurationParameter.getPublicKey())
                .build()
                .parseSignedClaims(token);

        final JwsHeader jwsHeader = claimsJws.getHeader();
        final Claims payload = claimsJws.getPayload();

        final Jwt jwt = new org.springframework.security.oauth2.jwt.Jwt(
                token,
                payload.getIssuedAt().toInstant(),
                payload.getExpiration().toInstant(),
                Map.of(
                        EnocaTokenClaims.TYP.getValue(), jwsHeader.getType(),
                        EnocaTokenClaims.ALGORITHM.getValue(), jwsHeader.getAlgorithm()
                ),
                payload
        );

        final UserType userType = UserType.valueOf(
                payload.get(EnocaTokenClaims.USER_TYPE.getValue()).toString()
        );

        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userType.name()));

        return UsernamePasswordAuthenticationToken
                .authenticated(jwt, null, authorities);

    }

    public void verifyAndValidate(
            final String jwt
    ) {
        Jwts.parser()
                .verifyWith(enocaTokenConfigurationParameter.getPublicKey())
                .build()
                .parseSignedClaims(jwt);

    }

    @Override
    public void verifyAndValidate(
            final Set<String> jwts
    ) {
        jwts.forEach(this::verifyAndValidate);
    }

    public Jws<Claims> getClaims(
            final String jwt
    ) {
        return Jwts.parser()
                .verifyWith(enocaTokenConfigurationParameter.getPublicKey())
                .build()
                .parseSignedClaims(jwt);
    }

    public Claims getPayload(
            final String jwt
    ) {
        return Jwts.parser()
                .verifyWith(enocaTokenConfigurationParameter.getPublicKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public String getId(
            final String jwt
    ) {
        return Jwts.parser()
                .verifyWith(enocaTokenConfigurationParameter.getPublicKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getId();
    }

}
