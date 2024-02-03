package org.moaydogdu.enoca_challenge.admin.service.auth.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.admin.exception.AdminAlreadyExistException;
import org.moaydogdu.enoca_challenge.admin.model.Admin;
import org.moaydogdu.enoca_challenge.admin.model.dto.request.AdminRegisterRequest;
import org.moaydogdu.enoca_challenge.admin.model.entity.AdminEntity;
import org.moaydogdu.enoca_challenge.admin.model.mapper.AdminDTOMapper;
import org.moaydogdu.enoca_challenge.admin.model.mapper.AdminMapper;
import org.moaydogdu.enoca_challenge.admin.repository.AdminRepository;
import org.moaydogdu.enoca_challenge.admin.service.auth.AdminRegisterService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminRegisterServiceImpl implements AdminRegisterService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Admin registerAdmin(
            final AdminRegisterRequest adminRegisterRequest
    ) {
        this.checkEmailUniqueness(adminRegisterRequest.getEmail());

        final AdminEntity adminEntityToBeSave = AdminDTOMapper
                .mapForSaving(adminRegisterRequest);

        adminEntityToBeSave.setPassword(
                passwordEncoder.encode(adminRegisterRequest.getPassword())
        );

        adminRepository.save(adminEntityToBeSave);

        return AdminMapper.toDomainModel(adminEntityToBeSave);
    }

    private void checkEmailUniqueness(
            final String email
    ) {
        if (adminRepository.existsAdminEntityByEmail(email))
        {
            throw new AdminAlreadyExistException("There is another admin with given email: " + email);
        }
    }

}
