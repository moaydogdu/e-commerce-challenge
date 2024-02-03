package org.moaydogdu.enoca_challenge.admin.service.auth;

import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaLoginRequest;

public interface AdminLoginService {
    EnocaToken login(
            final EnocaLoginRequest enocaLoginRequest
    );
}
