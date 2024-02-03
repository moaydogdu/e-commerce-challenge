package org.moaydogdu.enoca_challenge.admin.repository;

import org.moaydogdu.enoca_challenge.admin.model.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, String> {
    boolean existsAdminEntityByEmail(
            final String email
    );

    Optional<AdminEntity> findAdminEntityByEmail(
            final String email
    );
}
