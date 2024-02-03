package org.moaydogdu.enoca_challenge.auth.model.mapper;

import lombok.experimental.UtilityClass;
import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.dto.response.EnocaTokenResponse;

@UtilityClass
public class EnocaTokenDTOMapper {
    public static EnocaTokenResponse toResponse(
            final EnocaToken enocaToken
    ) {
        return EnocaTokenResponse.builder()
                .accessToken(enocaToken.getAccessToken())
                .accessTokenExpiresAt(enocaToken.getAccessTokenExpiresAt())
                .refreshToken(enocaToken.getRefreshToken())
                .build();
    }
}
