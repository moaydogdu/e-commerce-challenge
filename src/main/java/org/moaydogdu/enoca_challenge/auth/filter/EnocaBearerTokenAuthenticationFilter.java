package org.moaydogdu.enoca_challenge.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.service.EnocaInvalidTokenService;
import org.moaydogdu.enoca_challenge.auth.service.EnocaTokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnocaBearerTokenAuthenticationFilter extends OncePerRequestFilter {

    private final EnocaTokenService enocaTokenService;
    private final EnocaInvalidTokenService enocaInvalidTokenService;

    @Override
    protected void doFilterInternal(
            @NonNull final HttpServletRequest httpServletRequest,
            @NonNull final HttpServletResponse httpServletResponse,
            @NonNull final FilterChain filterChain
    ) throws ServletException, IOException {

        log.debug("API Request was secured with Enoca Security!");

        final String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (EnocaToken.isBearerToken(authorizationHeader))
        {
            final String jwt = EnocaToken.getJwt(authorizationHeader);

            enocaTokenService.verifyAndValidate(jwt);

            final String tokenId = enocaTokenService.getId(jwt);

            enocaInvalidTokenService.checkForInvalidityOfToken(tokenId);

            final UsernamePasswordAuthenticationToken authentication = enocaTokenService
                    .getAuthentication(jwt);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
}
