package org.moaydogdu.enoca_challenge.admin.service.auth;

import org.moaydogdu.enoca_challenge.admin.model.Admin;
import org.moaydogdu.enoca_challenge.admin.model.dto.request.AdminRegisterRequest;

public interface AdminRegisterService {
    Admin registerAdmin(
            final AdminRegisterRequest adminRegisterRequest
    );
}
