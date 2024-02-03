package org.moaydogdu.enoca_challenge.admin.model.mapper;

import lombok.experimental.UtilityClass;
import org.moaydogdu.enoca_challenge.admin.model.Admin;
import org.moaydogdu.enoca_challenge.admin.model.entity.AdminEntity;

import java.util.List;

@UtilityClass
public class AdminMapper {

    public static Admin toDomainModel(
            final AdminEntity adminEntity
    ) {
        return Admin.builder()
                .id(adminEntity.getId())
                .email(adminEntity.getEmail())
                .firstName(adminEntity.getFirstName())
                .lastName(adminEntity.getLastName())
                .phoneNumber(adminEntity.getPhoneNumber())
                .userType(adminEntity.getUserType())
                .userStatus(adminEntity.getUserStatus())
                .createdAt(adminEntity.getCreatedAt())
                .createdBy(adminEntity.getCreatedBy())
                .updatedAt(adminEntity.getUpdatedAt())
                .updatedBy(adminEntity.getUpdatedBy())
                .build();
    }

    public static List<Admin> toDomainModel(
            final List<AdminEntity> adminEntities
    ) {
        return adminEntities.stream()
                .map(AdminMapper::toDomainModel)
                .toList();
    }

    public static AdminEntity toEntity(
            final Admin admin
    ) {
        return AdminEntity.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .phoneNumber(admin.getPhoneNumber())
                .userType(admin.getUserType())
                .userStatus(admin.getUserStatus())
                .createdAt(admin.getCreatedAt())
                .createdBy(admin.getCreatedBy())
                .updatedAt(admin.getUpdatedAt())
                .updatedBy(admin.getUpdatedBy())
                .build();
    }
}
