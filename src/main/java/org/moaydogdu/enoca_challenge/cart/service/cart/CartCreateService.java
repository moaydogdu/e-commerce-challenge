package org.moaydogdu.enoca_challenge.cart.service.cart;

import org.moaydogdu.enoca_challenge.cart.model.Cart;
import org.moaydogdu.enoca_challenge.customer.model.Customer;

public interface CartCreateService {
    Cart createEmptyCartForCustomer(
            final Customer customer
    );
}
