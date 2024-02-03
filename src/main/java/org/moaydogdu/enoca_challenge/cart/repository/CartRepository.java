package org.moaydogdu.enoca_challenge.cart.repository;

import org.moaydogdu.enoca_challenge.cart.model.entity.CartEntity;
import org.moaydogdu.enoca_challenge.customer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {
    Optional<CartEntity> findCartEntityByCustomerEntity(
            final CustomerEntity customerEntity
    );
}
