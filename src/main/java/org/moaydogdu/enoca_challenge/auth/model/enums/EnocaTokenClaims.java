package org.moaydogdu.enoca_challenge.auth.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnocaTokenClaims {
    USER_ID("userId"),
    USER_TYPE("userType"),
    USER_STATUS("userStatus"),
    USER_FIRST_NAME("userFirstName"),
    USER_LAST_NAME("userLastName"),
    USER_EMAIL("userEmail"),
    USER_PHONE_NUMBER("userPhoneNumber"),
    STORE_TITLE("storeTitle"),
    ISSUED_AT("iat"),
    EXPIRES_AT("exp"),
    ALGORITHM("alg"),
    TYP("typ");

    private final String value;
}
