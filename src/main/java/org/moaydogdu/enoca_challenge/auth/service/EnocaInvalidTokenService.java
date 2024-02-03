package org.moaydogdu.enoca_challenge.auth.service;

import java.util.Set;

public interface EnocaInvalidTokenService {

    void invalidateTokens(
            final Set<String> tokenIds
    );

    void checkForInvalidityOfToken(
            final String tokenId
    );
}
