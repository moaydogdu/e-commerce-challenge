package org.moaydogdu.enoca_challenge.auth.config;

import lombok.Getter;
import org.moaydogdu.enoca_challenge.auth.model.enums.EnocaConfigurationParameter;
import org.moaydogdu.enoca_challenge.auth.util.KeyConverter;
import org.springframework.context.annotation.Configuration;

import java.security.PrivateKey;
import java.security.PublicKey;

@Getter
@Configuration
public class EnocaTokenConfigurationParameter {
    private final String issuer;
    private final int accessTokenExpireMinute;
    private final int refreshTokenExpireDay;
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public EnocaTokenConfigurationParameter() {
        this.issuer = EnocaConfigurationParameter.ENOCA_ISSUER.getDefaultValue();

        this.accessTokenExpireMinute = Integer.parseInt(
                EnocaConfigurationParameter.AUTH_ACCESS_TOKEN_EXPIRE_MINUTE.getDefaultValue()
        );

        this.refreshTokenExpireDay = Integer.parseInt(
                EnocaConfigurationParameter.AUTH_REFRESH_TOKEN_EXPIRE_DAY.getDefaultValue()
        );

        this.publicKey = KeyConverter.convertPublicKey(
                EnocaConfigurationParameter.AUTH_PUBLIC_KEY.getDefaultValue()
        );

        this.privateKey = KeyConverter.convertPrivateKey(
                EnocaConfigurationParameter.AUTH_PRIVATE_KEY.getDefaultValue()
        );
    }
}
