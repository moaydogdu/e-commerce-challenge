package org.moaydogdu.enoca_challenge.cart.service.cart.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.cart.model.Cart;
import org.moaydogdu.enoca_challenge.cart.model.entity.CartEntity;
import org.moaydogdu.enoca_challenge.cart.model.mapper.cart.CartDTOMapper;
import org.moaydogdu.enoca_challenge.cart.model.mapper.cart.CartMapper;
import org.moaydogdu.enoca_challenge.cart.repository.CartRepository;
import org.moaydogdu.enoca_challenge.cart.service.cart.CartCreateService;
import org.moaydogdu.enoca_challenge.customer.model.Customer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartCreateServiceImpl implements CartCreateService {

    private final CartRepository cartRepository;

    @Override
    public Cart createEmptyCartForCustomer(
            final Customer customer
    ) {
        final CartEntity cartEntityToBeSave = CartDTOMapper
                .mapForEmptyCreating(customer);

        cartRepository.save(cartEntityToBeSave);

        return CartMapper.toDomainModel(cartEntityToBeSave);
    }
}
