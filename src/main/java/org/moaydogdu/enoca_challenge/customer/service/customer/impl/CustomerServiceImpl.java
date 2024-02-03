package org.moaydogdu.enoca_challenge.customer.service.customer.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.customer.exception.CustomerNotFoundException;
import org.moaydogdu.enoca_challenge.customer.model.Customer;
import org.moaydogdu.enoca_challenge.customer.model.entity.CustomerEntity;
import org.moaydogdu.enoca_challenge.customer.model.mapper.CustomerMapper;
import org.moaydogdu.enoca_challenge.customer.repository.CustomerRepository;
import org.moaydogdu.enoca_challenge.customer.service.customer.CustomerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(
            final String customerId
    ) {
        final CustomerEntity customerEntityFromDB = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("With given id= " + customerId));

        return CustomerMapper.toDomainModel(customerEntityFromDB);
    }
}
