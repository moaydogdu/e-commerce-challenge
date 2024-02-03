package org.moaydogdu.enoca_challenge.cart.model.mapper.cart_item;

import lombok.experimental.UtilityClass;
import org.moaydogdu.enoca_challenge.cart.model.Cart;
import org.moaydogdu.enoca_challenge.cart.model.dto.request.CartItemCreateRequest;
import org.moaydogdu.enoca_challenge.cart.model.entity.CartItemEntity;
import org.moaydogdu.enoca_challenge.cart.model.mapper.cart.CartMapper;
import org.moaydogdu.enoca_challenge.product.model.Product;
import org.moaydogdu.enoca_challenge.product.model.mapper.ProductMapper;

@UtilityClass
public class CartItemDTOMapper {

    public static CartItemEntity mapForSaving(
            final Cart cart,
            final Product product,
            final CartItemCreateRequest cartItemCreateRequest
    ) {
        return CartItemEntity.builder()
                .cartEntity(CartMapper.toEntity(cart))
                .productEntity(ProductMapper.toEntity(product))
                .amount(cartItemCreateRequest.getAmount())
                .isOrdered(false)
                .build();
    }

}
