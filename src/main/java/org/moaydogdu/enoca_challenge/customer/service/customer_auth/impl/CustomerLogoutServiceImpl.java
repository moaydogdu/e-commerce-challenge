package org.moaydogdu.enoca_challenge.customer.service.customer_auth.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenInvalidateRequest;
import org.moaydogdu.enoca_challenge.auth.service.EnocaInvalidTokenService;
import org.moaydogdu.enoca_challenge.auth.service.EnocaTokenService;
import org.moaydogdu.enoca_challenge.customer.service.customer_auth.CustomerLogoutService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerLogoutServiceImpl implements CustomerLogoutService {

    private final EnocaTokenService enocaTokenService;
    private final EnocaInvalidTokenService enocaInvalidTokenService;

    @Override
    public void logoutCustomer(
            final EnocaTokenInvalidateRequest enocaTokenInvalidateRequest
    ) {
        enocaTokenService.verifyAndValidate(enocaTokenInvalidateRequest.getAccessToken());

        final String accessTokenId = enocaTokenService
                .getPayload(enocaTokenInvalidateRequest.getAccessToken())
                .getId();

        enocaInvalidTokenService.checkForInvalidityOfToken(accessTokenId);

        enocaTokenService.verifyAndValidate(enocaTokenInvalidateRequest.getRefreshToken());

        final String refreshTokenId = enocaTokenService
                .getPayload(enocaTokenInvalidateRequest.getRefreshToken())
                .getId();

        enocaInvalidTokenService.checkForInvalidityOfToken(refreshTokenId);


        enocaInvalidTokenService.invalidateTokens(Set.of(accessTokenId,refreshTokenId));

    }

}
