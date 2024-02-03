package org.moaydogdu.enoca_challenge.customer.service.customer_auth;

import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaLoginRequest;

public interface CustomerLoginService {
    EnocaToken login(
            final EnocaLoginRequest enocaLoginRequest
    );
}
