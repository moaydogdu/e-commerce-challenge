package org.moaydogdu.enoca_challenge.admin.model.mapper;

import lombok.experimental.UtilityClass;
import org.moaydogdu.enoca_challenge.admin.model.dto.request.AdminRegisterRequest;
import org.moaydogdu.enoca_challenge.admin.model.entity.AdminEntity;

@UtilityClass
public class AdminDTOMapper {

    public static AdminEntity mapForSaving(
            final AdminRegisterRequest adminRegisterRequest
    ) {
        return AdminEntity.builder()
                .email(adminRegisterRequest.getEmail())
                .firstName(adminRegisterRequest.getFirstName())
                .lastName(adminRegisterRequest.getLastName())
                .phoneNumber(adminRegisterRequest.getPhoneNumber())
                .build();
    }
}
