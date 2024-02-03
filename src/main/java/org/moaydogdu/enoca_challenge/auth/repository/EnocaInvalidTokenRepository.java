package org.moaydogdu.enoca_challenge.auth.repository;

import org.moaydogdu.enoca_challenge.auth.model.entity.EnocaInvalidTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnocaInvalidTokenRepository extends JpaRepository<EnocaInvalidTokenEntity, String> {

    Optional<EnocaInvalidTokenEntity> findByTokenId(
            final String tokenId
    );

}
