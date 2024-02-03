package org.moaydogdu.enoca_challenge.cart.repository;

import org.moaydogdu.enoca_challenge.cart.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, String> {
}
