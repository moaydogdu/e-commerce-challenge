package org.moaydogdu.enoca_challenge.common.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.moaydogdu.enoca_challenge.auth.model.enums.EnocaTokenClaims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @Column(name = "UPDATED_BY")
    private String updatedBy;


    @PrePersist
    public void prePersist() {
        this.createdBy = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(user -> !"anonymousUser".equals(user))
                .map(Jwt.class::cast)
                .map(jwt -> jwt.getClaim(EnocaTokenClaims.USER_ID.getValue()).toString())
                .orElse("ENOCA");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedBy = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(user -> !"anonymousUser".equals(user))
                .map(Jwt.class::cast)
                .map(jwt -> jwt.getClaim(EnocaTokenClaims.USER_ID.getValue()).toString())
                .orElse("ENOCA");
        this.updatedAt = LocalDateTime.now();
    }
}
