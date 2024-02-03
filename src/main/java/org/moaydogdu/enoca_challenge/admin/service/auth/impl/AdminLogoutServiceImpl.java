package org.moaydogdu.enoca_challenge.admin.service.auth.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.admin.service.auth.AdminLogoutService;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenInvalidateRequest;
import org.moaydogdu.enoca_challenge.auth.service.EnocaInvalidTokenService;
import org.moaydogdu.enoca_challenge.auth.service.EnocaTokenService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminLogoutServiceImpl implements AdminLogoutService {

    private final EnocaTokenService enocaTokenService;
    private final EnocaInvalidTokenService enocaInvalidTokenService;

    @Override
    public void logout(
            final EnocaTokenInvalidateRequest enocaTokenInvalidateRequest
    ) {
        enocaTokenService.verifyAndValidate(
                Set.of(
                        enocaTokenInvalidateRequest.getAccessToken(),
                        enocaTokenInvalidateRequest.getRefreshToken()
                )
        );

        final String accessTokenId = enocaTokenService
                .getPayload(enocaTokenInvalidateRequest.getAccessToken())
                .getId();

        enocaInvalidTokenService.checkForInvalidityOfToken(accessTokenId);


        final String refreshTokenId = enocaTokenService
                .getPayload(enocaTokenInvalidateRequest.getRefreshToken())
                .getId();

        enocaInvalidTokenService.checkForInvalidityOfToken(refreshTokenId);


        enocaInvalidTokenService.invalidateTokens(Set.of(accessTokenId,refreshTokenId));
    }


}
