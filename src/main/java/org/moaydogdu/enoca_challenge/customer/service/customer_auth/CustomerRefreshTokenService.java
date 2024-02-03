package org.moaydogdu.enoca_challenge.customer.service.customer_auth;

import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenRefreshRequest;

public interface CustomerRefreshTokenService {
    EnocaToken refreshAccessToken(
            final EnocaTokenRefreshRequest enocaTokenRefreshRequest
    );
}
