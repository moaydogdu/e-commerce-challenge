package org.moaydogdu.enoca_challenge.admin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.admin.model.dto.request.AdminRegisterRequest;
import org.moaydogdu.enoca_challenge.admin.service.auth.AdminLoginService;
import org.moaydogdu.enoca_challenge.admin.service.auth.AdminLogoutService;
import org.moaydogdu.enoca_challenge.admin.service.auth.AdminRefreshTokenService;
import org.moaydogdu.enoca_challenge.admin.service.auth.AdminRegisterService;
import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaLoginRequest;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenInvalidateRequest;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenRefreshRequest;
import org.moaydogdu.enoca_challenge.auth.model.dto.response.EnocaTokenResponse;
import org.moaydogdu.enoca_challenge.auth.model.mapper.EnocaTokenDTOMapper;
import org.moaydogdu.enoca_challenge.common.model.dto.response.EnocaResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication/admin")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminRegisterService adminRegisterService;
    private final AdminLoginService adminLoginService;
    private final AdminRefreshTokenService adminRefreshTokenService;
    private final AdminLogoutService adminLogoutService;

    @PostMapping("/register")
    public EnocaResponse<Void> registerAdmin(
            @RequestBody @Valid final AdminRegisterRequest adminRegisterRequest
    ) {
        adminRegisterService.registerAdmin(adminRegisterRequest);

        return EnocaResponse.SUCCESS;
    }

    @PostMapping("/login")
    public EnocaResponse<EnocaTokenResponse> loginAdmin(
            @RequestBody @Valid final EnocaLoginRequest enocaLoginRequest
    ) {
        final EnocaToken enocaToken = adminLoginService.login(enocaLoginRequest);

        final EnocaTokenResponse enocaTokenResponse = EnocaTokenDTOMapper
                .toResponse(enocaToken);

        return EnocaResponse.successOf(enocaTokenResponse);
    }

    @PostMapping("/refresh-token")
    public EnocaResponse<EnocaTokenResponse> refreshToken(
            @RequestBody @Valid final EnocaTokenRefreshRequest enocaTokenRefreshRequest
    ) {
        final EnocaToken enocaToken = adminRefreshTokenService
                .refreshToken(enocaTokenRefreshRequest);

        final EnocaTokenResponse enocaTokenResponse = EnocaTokenDTOMapper
                .toResponse(enocaToken);

        return EnocaResponse.successOf(enocaTokenResponse);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public EnocaResponse<Void> logout(
            @RequestBody @Valid final EnocaTokenInvalidateRequest enocaTokenInvalidateRequest
    ) {
        adminLogoutService.logout(enocaTokenInvalidateRequest);

        return EnocaResponse.SUCCESS;
    }
}
