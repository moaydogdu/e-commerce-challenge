package org.moaydogdu.enoca_challenge.customer.service.customer;

import org.moaydogdu.enoca_challenge.customer.model.Customer;

public interface CustomerService {
    Customer getCustomerById(
            final String customerId
    );
}
