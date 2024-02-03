package org.moaydogdu.enoca_challenge.customer.service.customer_auth.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.auth.exception.PasswordNotValidException;
import org.moaydogdu.enoca_challenge.auth.exception.UserStatusNotValidException;
import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaLoginRequest;
import org.moaydogdu.enoca_challenge.auth.model.enums.UserStatus;
import org.moaydogdu.enoca_challenge.auth.service.EnocaTokenService;
import org.moaydogdu.enoca_challenge.customer.exception.CustomerNotFoundException;
import org.moaydogdu.enoca_challenge.customer.model.entity.CustomerEntity;
import org.moaydogdu.enoca_challenge.customer.repository.CustomerRepository;
import org.moaydogdu.enoca_challenge.customer.service.customer_auth.CustomerLoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerLoginServiceImpl implements CustomerLoginService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final EnocaTokenService enocaTokenService;

    @Override
    public EnocaToken login(
            final EnocaLoginRequest enocaLoginRequest
    ) {
        final CustomerEntity customerEntityFromDB = customerRepository
                .findCustomerEntityByEmail(enocaLoginRequest.getEmail())
                .orElseThrow(CustomerNotFoundException::new);

        if (Boolean.FALSE.equals(
                passwordEncoder.matches(enocaLoginRequest.getPassword(), customerEntityFromDB.getPassword())
        ))
        {
            throw new PasswordNotValidException();
        }

        this.validateCustomerStatus(customerEntityFromDB);

        return enocaTokenService.generateToken(
                customerEntityFromDB.getClaims()
        );
    }

    private void validateCustomerStatus(
            final CustomerEntity customerEntity
    ) {
        if (Boolean.FALSE.equals(
                customerEntity.getUserStatus().equals(UserStatus.ACTIVE)
        ))
        {
            throw new UserStatusNotValidException("UserStatus = " + customerEntity.getUserStatus());
        }
    }
}
