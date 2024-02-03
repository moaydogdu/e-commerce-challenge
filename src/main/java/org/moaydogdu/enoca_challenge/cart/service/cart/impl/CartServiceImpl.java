package org.moaydogdu.enoca_challenge.cart.service.cart.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.auth.model.EnocaIdentity;
import org.moaydogdu.enoca_challenge.cart.exception.CartNotFoundException;
import org.moaydogdu.enoca_challenge.cart.model.Cart;
import org.moaydogdu.enoca_challenge.cart.model.entity.CartEntity;
import org.moaydogdu.enoca_challenge.cart.model.mapper.cart.CartMapper;
import org.moaydogdu.enoca_challenge.cart.repository.CartRepository;
import org.moaydogdu.enoca_challenge.cart.service.cart.CartService;
import org.moaydogdu.enoca_challenge.customer.model.Customer;
import org.moaydogdu.enoca_challenge.customer.model.mapper.CustomerMapper;
import org.moaydogdu.enoca_challenge.customer.service.customer.CustomerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CustomerService customerService;

    private final EnocaIdentity enocaIdentity;

    private String getCustomerId()
    {
        return enocaIdentity.getUserId();
    }

    @Override
    public Cart getCartOfRequestOwnerCustomer() {
        final String customerId = getCustomerId();

        final Customer requestOwnerCustomer = customerService
                .getCustomerById(customerId);

        final CartEntity cartEntityByCustomer = cartRepository
                .findCartEntityByCustomerEntity(
                        CustomerMapper.toEntity(requestOwnerCustomer)
                )
                .orElseThrow(CartNotFoundException::new);

        return CartMapper.toDomainModel(cartEntityByCustomer);
    }
}
