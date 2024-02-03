package org.moaydogdu.enoca_challenge.admin.service.auth;

import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenRefreshRequest;

public interface AdminRefreshTokenService {
    EnocaToken refreshToken(
            final EnocaTokenRefreshRequest enocaTokenRefreshRequest
    );
}
