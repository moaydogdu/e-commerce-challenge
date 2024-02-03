package org.moaydogdu.enoca_challenge.customer.service.customer_auth;

import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenInvalidateRequest;

public interface CustomerLogoutService {
    void logoutCustomer(
            final EnocaTokenInvalidateRequest enocaTokenInvalidateRequest
    );
}
