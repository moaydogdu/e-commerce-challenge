package org.moaydogdu.enoca_challenge.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.auth.exception.TokenAlreadyInvalidatedException;
import org.moaydogdu.enoca_challenge.auth.model.entity.EnocaInvalidTokenEntity;
import org.moaydogdu.enoca_challenge.auth.repository.EnocaInvalidTokenRepository;
import org.moaydogdu.enoca_challenge.auth.service.EnocaInvalidTokenService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnocaInvalidTokenServiceImpl implements EnocaInvalidTokenService {

    private final EnocaInvalidTokenRepository enocaInvalidTokenRepository;

    @Override
    public void invalidateTokens(
            final Set<String> tokenIds
    ) {
        final Set<EnocaInvalidTokenEntity> enocaInvalidTokenEntities = tokenIds.stream()
                .map(tokenId -> EnocaInvalidTokenEntity.builder()
                        .tokenId(tokenId)
                        .build()
                )
                .collect(Collectors.toSet());

        enocaInvalidTokenRepository.saveAll(enocaInvalidTokenEntities);
    }

    @Override
    public void checkForInvalidityOfToken(
            final String tokenId
    ) {
        final boolean isTokenInvalid = enocaInvalidTokenRepository.findByTokenId(tokenId).isPresent();

        if (isTokenInvalid)
        {
            throw new TokenAlreadyInvalidatedException(tokenId);
        }
    }
}
