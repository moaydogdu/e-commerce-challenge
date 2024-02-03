package org.moaydogdu.enoca_challenge.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaLoginRequest;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenInvalidateRequest;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenRefreshRequest;
import org.moaydogdu.enoca_challenge.auth.model.dto.response.EnocaTokenResponse;
import org.moaydogdu.enoca_challenge.auth.model.mapper.EnocaTokenDTOMapper;
import org.moaydogdu.enoca_challenge.common.model.dto.response.EnocaResponse;
import org.moaydogdu.enoca_challenge.customer.model.dto.request.CustomerRegisterRequest;
import org.moaydogdu.enoca_challenge.customer.service.customer_auth.CustomerLoginService;
import org.moaydogdu.enoca_challenge.customer.service.customer_auth.CustomerLogoutService;
import org.moaydogdu.enoca_challenge.customer.service.customer_auth.CustomerRefreshTokenService;
import org.moaydogdu.enoca_challenge.customer.service.customer_auth.CustomerRegisterService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication/customer")
@RequiredArgsConstructor
public class CustomerAuthController {

    private final CustomerRegisterService customerRegisterService;
    private final CustomerLoginService customerLoginService;
    private final CustomerRefreshTokenService customerRefreshTokenService;
    private final CustomerLogoutService customerLogoutService;

    @PostMapping("/register")
    public EnocaResponse<Void> register(
            @RequestBody @Valid final CustomerRegisterRequest customerRegisterRequest
    ) {
        customerRegisterService.registerCustomer(customerRegisterRequest);

        return EnocaResponse.SUCCESS;
    }

    @PostMapping("/login")
    public EnocaResponse<EnocaTokenResponse> login(
            @RequestBody @Valid final EnocaLoginRequest enocaLoginRequest
    ) {
        final EnocaToken enocaToken = customerLoginService
                .login(enocaLoginRequest);

        final EnocaTokenResponse enocaTokenResponse = EnocaTokenDTOMapper
                .toResponse(enocaToken);

        return EnocaResponse.successOf(enocaTokenResponse);
    }

    @PostMapping("/refresh-token")
    public EnocaResponse<EnocaTokenResponse> refreshToken(
            @RequestBody @Valid final EnocaTokenRefreshRequest enocaTokenRefreshRequest
    ) {
        final EnocaToken enocaToken = customerRefreshTokenService
                .refreshAccessToken(enocaTokenRefreshRequest);

        final EnocaTokenResponse enocaTokenResponse = EnocaTokenDTOMapper
                .toResponse(enocaToken);

        return EnocaResponse.successOf(enocaTokenResponse);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public EnocaResponse<Void> invalidateTokens(
            @RequestBody @Valid final EnocaTokenInvalidateRequest enocaTokenInvalidateRequest
    ) {
        customerLogoutService.logoutCustomer(enocaTokenInvalidateRequest);

        return EnocaResponse.SUCCESS;
    }


}
