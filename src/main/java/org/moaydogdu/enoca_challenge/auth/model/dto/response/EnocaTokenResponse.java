package org.moaydogdu.enoca_challenge.auth.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnocaTokenResponse {

    private String accessToken;
    private Long accessTokenExpiresAt;
    private String refreshToken;
}
