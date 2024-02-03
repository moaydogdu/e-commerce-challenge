package org.moaydogdu.enoca_challenge.product.repository;

import org.moaydogdu.enoca_challenge.product.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    boolean existsProductEntityByName(
            final String name
    );
}
