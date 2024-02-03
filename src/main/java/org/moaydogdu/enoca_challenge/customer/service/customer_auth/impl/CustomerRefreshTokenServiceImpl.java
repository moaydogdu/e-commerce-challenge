package org.moaydogdu.enoca_challenge.customer.service.customer_auth.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.auth.exception.UserStatusNotValidException;
import org.moaydogdu.enoca_challenge.auth.model.EnocaToken;
import org.moaydogdu.enoca_challenge.auth.model.dto.request.EnocaTokenRefreshRequest;
import org.moaydogdu.enoca_challenge.auth.model.enums.EnocaTokenClaims;
import org.moaydogdu.enoca_challenge.auth.model.enums.UserStatus;
import org.moaydogdu.enoca_challenge.auth.service.EnocaTokenService;
import org.moaydogdu.enoca_challenge.customer.exception.CustomerNotFoundException;
import org.moaydogdu.enoca_challenge.customer.model.entity.CustomerEntity;
import org.moaydogdu.enoca_challenge.customer.repository.CustomerRepository;
import org.moaydogdu.enoca_challenge.customer.service.customer_auth.CustomerRefreshTokenService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerRefreshTokenServiceImpl implements CustomerRefreshTokenService {

    private final EnocaTokenService enocaTokenService;
    private final CustomerRepository customerRepository;

    @Override
    public EnocaToken refreshAccessToken(
            final EnocaTokenRefreshRequest enocaTokenRefreshRequest
    ) {
        enocaTokenService.verifyAndValidate(enocaTokenRefreshRequest.getRefreshToken());

        final String customerId = enocaTokenService
                .getPayload(enocaTokenRefreshRequest.getRefreshToken())
                .get(EnocaTokenClaims.USER_ID.getValue())
                .toString();

        final CustomerEntity customerEntityFromDB = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("CustomerID = " + customerId));

        this.validateCustomerStatus(customerEntityFromDB);

        return enocaTokenService.generateToken(
                customerEntityFromDB.getClaims(),
                enocaTokenRefreshRequest.getRefreshToken()
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
