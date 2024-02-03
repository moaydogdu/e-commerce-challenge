package org.moaydogdu.enoca_challenge.customer.service.customer_auth.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.cart.service.cart.CartCreateService;
import org.moaydogdu.enoca_challenge.customer.exception.CustomerAlreadyExistException;
import org.moaydogdu.enoca_challenge.customer.model.Customer;
import org.moaydogdu.enoca_challenge.customer.model.dto.request.CustomerRegisterRequest;
import org.moaydogdu.enoca_challenge.customer.model.entity.CustomerEntity;
import org.moaydogdu.enoca_challenge.customer.model.mapper.CustomerDTOMapper;
import org.moaydogdu.enoca_challenge.customer.model.mapper.CustomerMapper;
import org.moaydogdu.enoca_challenge.customer.repository.CustomerRepository;
import org.moaydogdu.enoca_challenge.customer.service.customer_auth.CustomerRegisterService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerRegisterServiceImpl implements CustomerRegisterService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    private final CartCreateService cartCreateService;

    @Override
    @Transactional
    public Customer registerCustomer(
            final CustomerRegisterRequest customerRegisterRequest
    ) {
        this.checkEmailUniqueness(customerRegisterRequest.getEmail());

        final CustomerEntity customerEntityToBeSave = CustomerDTOMapper
                .mapForSaving(customerRegisterRequest);

        customerEntityToBeSave.setPassword(
                passwordEncoder.encode(customerRegisterRequest.getPassword())
        );

        customerRepository.save(customerEntityToBeSave);

        final Customer customerDomainModel = CustomerMapper.toDomainModel(customerEntityToBeSave);

        cartCreateService.createEmptyCartForCustomer(customerDomainModel);

        return customerDomainModel;
    }

    private void checkEmailUniqueness(
            final String email
    ) {
        if (customerRepository.existsCustomerEntityByEmail(email))
        {
            throw new CustomerAlreadyExistException("There is another customer with given email address {"+email+"}");
        }
    }
}
