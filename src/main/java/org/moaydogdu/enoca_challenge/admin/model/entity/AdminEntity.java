package org.moaydogdu.enoca_challenge.admin.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.moaydogdu.enoca_challenge.auth.model.enums.EnocaTokenClaims;
import org.moaydogdu.enoca_challenge.auth.model.enums.UserStatus;
import org.moaydogdu.enoca_challenge.auth.model.enums.UserType;
import org.moaydogdu.enoca_challenge.common.model.entity.BaseEntity;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ENOCA_ADMIN")
public class AdminEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(
            name = "PHONE_NUMBER",
            length = 20
    )
    private String phoneNumber;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.ADMIN;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.ACTIVE;

    public Map<String, Object> getClaims()
    {
        final Map<String, Object> claims = new HashMap<>();

        claims.put(EnocaTokenClaims.USER_ID.getValue(), this.id);
        claims.put(EnocaTokenClaims.USER_TYPE.getValue(), this.userType);
        claims.put(EnocaTokenClaims.USER_STATUS.getValue(), this.userStatus);
        claims.put(EnocaTokenClaims.USER_FIRST_NAME.getValue(), this.firstName);
        claims.put(EnocaTokenClaims.USER_LAST_NAME.getValue(), this.lastName);
        claims.put(EnocaTokenClaims.USER_EMAIL.getValue(), this.email);
        claims.put(EnocaTokenClaims.USER_PHONE_NUMBER.getValue(), this.phoneNumber);

        return claims;
    }
}
