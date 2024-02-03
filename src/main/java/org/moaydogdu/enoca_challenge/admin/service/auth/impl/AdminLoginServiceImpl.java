package org.moaydogdu.enoca_challenge.admin.service.auth.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.admin.exception.AdminNotFoundException;
import org.moaydogdu.enoca_challenge.admin.model.entity.AdminEntity;
import org.moaydogdu.enoca_challenge.admin.repository.AdminRepository;
import org.moaydogdu.enoca_challenge.admin.service.auth.AdminLoginService;
import org.moaydogdu.enoca_challenge.auth.exception.PasswordNotValidException;
import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaLoginRequest;
import org.moaydogdu.enoca_challenge.auth.service.EnocaTokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLoginServiceImpl implements AdminLoginService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final EnocaTokenService enocaTokenService;

    @Override
    public EnocaToken login(
            final EnocaLoginRequest enocaLoginRequest
    ) {
        final AdminEntity adminEntityFromDB = adminRepository
                .findAdminEntityByEmail(enocaLoginRequest.getEmail())
                .orElseThrow(
                        () -> new AdminNotFoundException("Can't find with given email: "
                        + enocaLoginRequest.getEmail())
                );

        if (Boolean.FALSE.equals(
                passwordEncoder.matches(
                        enocaLoginRequest.getPassword(), adminEntityFromDB.getPassword()
                )
        )) {
            throw new PasswordNotValidException();
        }

        return enocaTokenService.generateToken(adminEntityFromDB.getClaims());
    }
}
