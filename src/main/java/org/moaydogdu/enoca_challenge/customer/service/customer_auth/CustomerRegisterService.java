package org.moaydogdu.enoca_challenge.customer.service.customer_auth;

import org.moaydogdu.enoca_challenge.customer.model.Customer;
import org.moaydogdu.enoca_challenge.customer.model.dto.request.CustomerRegisterRequest;

public interface CustomerRegisterService {
    Customer registerCustomer(
            final CustomerRegisterRequest customerRegisterRequest
    );
}
