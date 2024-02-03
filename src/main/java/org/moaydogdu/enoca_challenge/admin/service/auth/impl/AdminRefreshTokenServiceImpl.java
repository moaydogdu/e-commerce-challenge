package org.moaydogdu.enoca_challenge.admin.service.auth.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.admin.exception.AdminNotFoundException;
import org.moaydogdu.enoca_challenge.admin.model.entity.AdminEntity;
import org.moaydogdu.enoca_challenge.admin.repository.AdminRepository;
import org.moaydogdu.enoca_challenge.admin.service.auth.AdminRefreshTokenService;
import org.moaydogdu.enoca_challenge.auth.exception.UserStatusNotValidException;
import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenRefreshRequest;
import org.moaydogdu.enoca_challenge.auth.model.enums.EnocaTokenClaims;
import org.moaydogdu.enoca_challenge.auth.model.enums.UserStatus;
import org.moaydogdu.enoca_challenge.auth.service.EnocaTokenService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminRefreshTokenServiceImpl implements AdminRefreshTokenService {

    private final AdminRepository adminRepository;
    private final EnocaTokenService enocaTokenService;

    @Override
    public EnocaToken refreshToken(
            final EnocaTokenRefreshRequest enocaTokenRefreshRequest
    ) {
        enocaTokenService.verifyAndValidate(enocaTokenRefreshRequest.getRefreshToken());

        final String adminId = enocaTokenService
                .getPayload(enocaTokenRefreshRequest.getRefreshToken())
                .get(EnocaTokenClaims.USER_ID.getValue())
                .toString();

        final AdminEntity adminEntityFromDB = adminRepository
                .findById(adminId)
                .orElseThrow(AdminNotFoundException::new);

        this.validateAdminStatus(adminEntityFromDB);

        return enocaTokenService.generateToken(
                adminEntityFromDB.getClaims(),
                enocaTokenRefreshRequest.getRefreshToken()
        );
    }

    private void validateAdminStatus(
            final AdminEntity adminEntity
    ) {
        if (!(UserStatus.ACTIVE.equals(adminEntity.getUserStatus()))) {
            throw new UserStatusNotValidException("UserStatus = " + adminEntity.getUserStatus());
        }
    }
}
