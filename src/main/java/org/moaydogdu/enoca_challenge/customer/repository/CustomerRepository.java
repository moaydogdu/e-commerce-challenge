package org.moaydogdu.enoca_challenge.customer.repository;

import org.moaydogdu.enoca_challenge.customer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    boolean existsCustomerEntityByEmail(
            final String email
    );

    Optional<CustomerEntity> findCustomerEntityByEmail(
            final String email
    );
}
