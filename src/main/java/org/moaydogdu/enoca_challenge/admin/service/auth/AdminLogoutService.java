package org.moaydogdu.enoca_challenge.admin.service.auth;

import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenInvalidateRequest;

public interface AdminLogoutService {
    void logout(
            final EnocaTokenInvalidateRequest enocaTokenInvalidateRequest
    );
}
