package org.moaydogdu.enoca_challenge.auth.model;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.auth.model.enums.EnocaTokenClaims;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class EnocaIdentity {

    public String getAccessToken() {
        return this.getJwt().getTokenValue();
    }

    public String getUserId() {
        return this.getJwt().getClaim(EnocaTokenClaims.USER_ID.getValue());
    }


    private Jwt getJwt() {
        return ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
