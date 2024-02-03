package org.moaydogdu.enoca_challenge.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.moaydogdu.enoca_challenge.auth.model.enums.UserStatus;
import org.moaydogdu.enoca_challenge.auth.model.enums.UserType;
import org.moaydogdu.enoca_challenge.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseDomainModel {

    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Builder.Default
    private UserType userType = UserType.CUSTOMER;
    @Builder.Default
    private UserStatus userStatus = UserStatus.ACTIVE;
}
