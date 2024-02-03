package org.moaydogdu.enoca_challenge.cart.service.cart_item.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.cart.model.Cart;
import org.moaydogdu.enoca_challenge.cart.model.CartItem;
import org.moaydogdu.enoca_challenge.cart.model.dto.request.CartItemCreateRequest;
import org.moaydogdu.enoca_challenge.cart.model.entity.CartItemEntity;
import org.moaydogdu.enoca_challenge.cart.model.mapper.cart_item.CartItemDTOMapper;
import org.moaydogdu.enoca_challenge.cart.model.mapper.cart_item.CartItemMapper;
import org.moaydogdu.enoca_challenge.cart.repository.CartItemRepository;
import org.moaydogdu.enoca_challenge.cart.service.cart_item.CartItemCreateService;
import org.moaydogdu.enoca_challenge.product.model.Product;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemCreateServiceImpl implements CartItemCreateService {

    private final CartItemRepository cartItemRepository;


    @Override
    public CartItem createCartItem(
            final Product product,
            final Cart cart,
            final CartItemCreateRequest cartItemCreateRequest
    ) {
        final CartItemEntity cartItemToBeSave = CartItemDTOMapper
                .mapForSaving(
                        cart,
                        product,
                        cartItemCreateRequest
                );

        this.calculateTotalPriceOfCartItem(cartItemToBeSave);

        cartItemRepository.save(cartItemToBeSave);

        return CartItemMapper.toDomainModel(cartItemToBeSave);
    }

    private void calculateTotalPriceOfCartItem(
            final CartItemEntity cartItemToBeSave
    ) {
        cartItemToBeSave.setTotalPrice(
                cartItemToBeSave.getProductEntity().getUnitPrice()
                        .multiply(cartItemToBeSave.getAmount())
        );
    }
}
